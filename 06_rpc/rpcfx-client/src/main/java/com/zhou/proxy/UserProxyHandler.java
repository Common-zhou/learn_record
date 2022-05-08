package com.zhou.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhou.api.common.RpcfxRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoubing
 * @date 2022-05-09 00:36
 */
public class UserProxyHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcfxRequest rpcfxRequest = new RpcfxRequest();
        rpcfxRequest.setServiceClass("com.zhou.api.service.UserService");
        rpcfxRequest.setParams(args);
        rpcfxRequest.setMethodName(method.getName());
        HttpPost httpPost = new HttpPost("http://localhost:8080");

        httpPost.setHeader("Content-Type", "application/json");

        List<NameValuePair> nvps = new ArrayList<>();
//        nvps.add(new BasicNameValuePair("serviceClass", "com.zhou.api.service.UserService"));
//        nvps.add(new BasicNameValuePair("params", JSON.toJSONString(args)));
//        nvps.add(new BasicNameValuePair("methodName", method.getName()));
//        httpPost.setEntity(new UrlEncodedFormEntity(nvps));

        httpPost.setEntity(new StringEntity(JSON.toJSONString(rpcfxRequest, SerializerFeature.WriteClassName)));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpPost);

        String res = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println("get reponse :" + res);


        return null;
    }
}
