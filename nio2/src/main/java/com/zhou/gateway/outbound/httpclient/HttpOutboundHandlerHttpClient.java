package com.zhou.gateway.outbound.httpclient;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;


import com.zhou.gateway.outbound.HttpOutboundHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author zhoubing
 * @date 2022-03-27 23:20
 */
public class HttpOutboundHandlerHttpClient implements HttpOutboundHandler {
    private String proxyServer;
    private CloseableHttpClient httpclient;

    public HttpOutboundHandlerHttpClient(String proxyServer) {
        this.proxyServer = proxyServer;
        this.httpclient = HttpClients.createDefault();
    }

    @Override
    public void handle(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        //这里拿到了请求
        try {
            doHttpRequest(fullHttpRequest, ctx);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private DefaultFullHttpResponse doHttpRequest(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx)
        throws IOException {
        String uri = fullHttpRequest.uri();

        DefaultFullHttpResponse response = null;
        CloseableHttpResponse endpointResponse = null;
        try {
            HttpGet httpGet = new HttpGet(this.proxyServer + "/" + uri);

            endpointResponse = httpclient.execute(httpGet);

            HttpEntity entity1 = endpointResponse.getEntity();

            byte[] bytes = EntityUtils.toByteArray(entity1);

            response =
                new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(bytes));
            response.headers().set("Content-Type", "application/json");
            response.headers()
                .set("Content-Length", Integer.parseInt(endpointResponse.getFirstHeader("Content-Length").getValue()));

        } catch (Exception e) {
            response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        } finally {
            if (endpointResponse != null) {
                endpointResponse.close();
            }

            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
            return response;
        }
    }
}
