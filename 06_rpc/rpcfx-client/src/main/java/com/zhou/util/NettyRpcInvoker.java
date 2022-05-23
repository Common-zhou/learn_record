package com.zhou.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhou.api.common.RpcfxRequest;
import com.zhou.api.common.RpcfxResponse;
import lombok.experimental.UtilityClass;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author zhoubing
 * @date 2022-05-19 08:11
 */
@UtilityClass
public class NettyRpcInvoker {
    public RpcfxResponse post(String url, RpcfxRequest rpcfxRequest) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(JSON.toJSONString(rpcfxRequest, SerializerFeature.WriteClassName)));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpPost);

        String res = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println("get reponse :" + res);

        RpcfxResponse rpcfxResponse = JSON.parseObject(res, RpcfxResponse.class);
        return rpcfxResponse;
    }
}
