package com.zhou;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author zhoubing
 * @date 2022-03-26 22:42
 */
public class HttpClientTest {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("http://localhost:8801");
        CloseableHttpResponse execute = httpClient.execute(httpPost);
        for (Header allHeader : execute.getAllHeaders()) {
            System.out.println(allHeader);
        }

        HttpEntity entity = execute.getEntity();
        InputStream content = entity.getContent();

        StringBuilder stringBuilder = new StringBuilder();
        int len = -1;
        byte[] bytes = new byte[2 * 1024];
        while ((len = content.read(bytes)) != -1){
            // 当不接触到最终
            stringBuilder.append(new String(bytes, 0, len));
        }

        System.out.println(stringBuilder);

    }
}
