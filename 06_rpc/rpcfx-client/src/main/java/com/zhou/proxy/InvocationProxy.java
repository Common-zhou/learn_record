package com.zhou.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.zhou.api.common.RpcfxRequest;
import com.zhou.api.common.RpcfxResponse;
import com.zhou.util.HttpRpcInvoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhoubing
 * @date 2022-05-09 00:31
 */

public class InvocationProxy {

    static {
        ParserConfig.getGlobalInstance().addAccept("com.zhou");
    }

    public static <T> T create(Class<?> serviceClass, String url) {
        return (T) Proxy.newProxyInstance(InvocationProxy.class.getClassLoader(), new Class[]{serviceClass},
                new InvocationProxyHandler(serviceClass, url));
    }

    static class InvocationProxyHandler implements InvocationHandler {
        private Class<?> serviceClass;
        private String url;

        public InvocationProxyHandler(Class<?> serviceClass, String url) {
            this.serviceClass = serviceClass;
            this.url = url;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            RpcfxRequest rpcfxRequest = new RpcfxRequest();
            rpcfxRequest.setServiceClass(this.serviceClass.getName());
            rpcfxRequest.setParams(args);
            rpcfxRequest.setMethodName(method.getName());

            RpcfxResponse rpcfxResponse = HttpRpcInvoker.post(url, rpcfxRequest);

            if (rpcfxResponse.getStatus() != 0) {
                System.out.println(rpcfxResponse.getException());
                // 代表有异常
            }

            return JSON.parse(rpcfxResponse.getResult());
        }
    }

}
