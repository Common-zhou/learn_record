package com.zhou.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhou.api.common.RpcfxRequest;
import com.zhou.api.common.RpcfxResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

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
            HttpPost httpPost = new HttpPost(url);

            httpPost.setHeader("Content-Type", "application/json");

            httpPost.setEntity(new StringEntity(JSON.toJSONString(rpcfxRequest, SerializerFeature.WriteClassName)));

            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpPost);

            String res = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("get reponse :" + res);

            RpcfxResponse rpcfxResponse = JSON.parseObject(res, RpcfxResponse.class);
            if (rpcfxResponse.getStatus() != 0) {
                System.out.println(rpcfxResponse.getException());
                // 代表有异常
            }

            return JSON.parse(rpcfxResponse.getResult());
        }
    }

}
