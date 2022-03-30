package com.zhou.gateway.outbound.httpclient;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;


import com.zhou.gateway.filter.HttpResponseFilter;
import com.zhou.gateway.filter.HttpResponseHeaderFilter;
import com.zhou.gateway.outbound.HttpOutboundHandler;
import com.zhou.gateway.router.HttpEndpointRouter;
import com.zhou.gateway.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import java.io.IOException;
import java.util.List;
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
    private List<String> proxyServers;
    private CloseableHttpClient httpclient;
    private HttpResponseFilter filter;
    private HttpEndpointRouter router;

    public HttpOutboundHandlerHttpClient(List<String> proxyServers) {
        this.proxyServers = proxyServers;
        this.httpclient = HttpClients.createDefault();
        this.filter = new HttpResponseHeaderFilter();
        this.router = new RandomHttpEndpointRouter();
    }

    @Override
    public void handle(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        //这里拿到了请求
        DefaultFullHttpResponse response = null;
        try {
            response = doHttpRequest(fullHttpRequest, ctx);
            filter.filter(response);
        } catch (IOException e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
        }



    }

    private DefaultFullHttpResponse doHttpRequest(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx)
        throws IOException {
        String uri = fullHttpRequest.uri();

        DefaultFullHttpResponse response = null;
        CloseableHttpResponse endpointResponse = null;
        try {

            String route = router.route(this.proxyServers);

            HttpGet httpGet = new HttpGet(route + "/" + uri);

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
        }
        return response;
    }
}
