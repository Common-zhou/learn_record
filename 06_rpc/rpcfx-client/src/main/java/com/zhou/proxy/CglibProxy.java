package com.zhou.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.zhou.api.common.RpcfxRequest;
import com.zhou.api.common.RpcfxResponse;
import com.zhou.util.HttpRpcInvoker;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhoubing
 * @date 2022-05-10 09:10
 */
public class CglibProxy {
    static {
        ParserConfig.getGlobalInstance().addAccept("com.zhou");
    }

    public static <T> T create(Class<?> serviceClass, String url) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(serviceClass);
        enhancer.setCallback(new AopMethodInterceptor(url));

        return (T) enhancer.create();
    }

    public static class AopMethodInterceptor implements MethodInterceptor {

        private final String url;

        public <T> AopMethodInterceptor(String url) {
            this.url = url;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(method.getDeclaringClass().getName());
            request.setMethodName(method.getName());
            request.setParams(objects);
            RpcfxResponse response = HttpRpcInvoker.post(url, request);
            return JSON.parse(response.getResult().toString());
        }
    }
}
