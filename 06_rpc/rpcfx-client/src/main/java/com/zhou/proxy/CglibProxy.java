package com.zhou.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhou.api.common.ProxyContext;
import com.zhou.api.common.RpcfxRequest;
import com.zhou.api.common.RpcfxResponse;
import com.zhou.api.filter.LogFilter;
import com.zhou.api.router.RandomRouter;
import com.zhou.util.HttpRpcInvoker;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zhoubing
 * @date 2022-05-10 09:10
 */
public class CglibProxy {
    static {
        ParserConfig.getGlobalInstance().addAccept("com.zhou");
    }

    public static <T> T create(Class<?> serviceClass, List<String> urls) {
        return create(serviceClass, getDefaultContext(urls));
    }

    private static ProxyContext getDefaultContext(List<String> urls) {
        ProxyContext context = new ProxyContext();
        context.setFilter(new LogFilter());
        context.setRouter(new RandomRouter());
        context.setUrls(urls);

        return context;
    }

    public static <T> T create(Class<?> serviceClass, ProxyContext context) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(serviceClass);
        enhancer.setCallback(new AopMethodInterceptor(context));

        return (T) enhancer.create();
    }

    public static class AopMethodInterceptor implements MethodInterceptor {

        private final ProxyContext context;

        public <T> AopMethodInterceptor(ProxyContext context) {
            this.context = context;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(method.getDeclaringClass().getName());
            request.setMethodName(method.getName());
            request.setParams(objects);

            if (context.getFilter() != null) {
                if (!context.getFilter().filter(request)) {
                    // 直接拦截掉
                    return "filter not passed";
                }
            }

            String url = context.getUrls().get(0);
            if (context.getRouter() != null) {
                url = context.getRouter().route(context.getUrls());
            }

            RpcfxResponse response = HttpRpcInvoker.post(url, request);

            if (response != null && response.getStatus() == 0) {
                return JSON.parse(response.getResult());
            }

            return JSON.toJSONString(response, SerializerFeature.WriteClassName);
        }
    }
}
