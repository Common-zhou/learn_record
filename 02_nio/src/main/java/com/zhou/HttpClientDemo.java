package com.zhou;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 主要测试使用httpclient 调用接口。
 * 主要参考： 官网文档 quickstart
 * https://hc.apache.org/httpcomponents-client-4.5.x/quickstart.html
 * 这里主要是仿照官网文档来的。
 * @author zhoubing
 * @date 2022-03-26 22:59
 */
public class HttpClientDemo {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8801");
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        // The underlying HTTP connection is still held by the response object
        // to allow the response content to be streamed directly from the network socket.
        // In order to ensure correct deallocation of system resources
        // the user MUST call CloseableHttpResponse#close() from a finally clause.
        // Please note that if response content is not fully consumed the underlying
        // connection cannot be safely re-used and will be shut down and discarded
        // by the connection manager.
        try {
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            //System.out.println(IOUtil.readInputStream(entity1.getContent()));

            String s = EntityUtils.toString(entity1, "utf-8");
            System.out.println(s);
            EntityUtils.consume(entity1);

        } finally {
            response1.close();
        }

        //HttpPost httpPost = new HttpPost("http://targethost/login");
        //List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        //nvps.add(new BasicNameValuePair("username", "vip"));
        //nvps.add(new BasicNameValuePair("password", "secret"));
        //httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        //CloseableHttpResponse response2 = httpclient.execute(httpPost);
        //
        //try {
        //    System.out.println(response2.getStatusLine());
        //    HttpEntity entity2 = response2.getEntity();
        //    // do something useful with the response body
        //    // and ensure it is fully consumed
        //    EntityUtils.consume(entity2);
        //} finally {
        //    response2.close();
        //}
    }

}
