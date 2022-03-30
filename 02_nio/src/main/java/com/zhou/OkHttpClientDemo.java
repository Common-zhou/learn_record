package com.zhou;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * okhttp 的官网demo 参考自以下文档
 * https://square.github.io/okhttp/
 * @author zhoubing
 * @date 2022-03-27 10:39
 */
public class OkHttpClientDemo {


    public static void main(String[] args) throws IOException {
        String res = run("http://localhost:8801");
        System.out.println(res);
    }

    static String run(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(url)
            .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
