package com.zhou.gateway.inbound;

import com.zhou.gateway.filter.HttpInputHeaderFilter;
import com.zhou.gateway.filter.HttpRequestFilter;
import com.zhou.gateway.outbound.HttpOutboundHandler;
import com.zhou.gateway.outbound.httpclient.HttpOutboundHandlerHttpClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 这里是真实的。调用其他接口 获取数据的地方。
 *
 * @author zhoubing
 * @date 2022-03-27 23:11
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(HttpInboundInitializer.class);
    private List<String> proxyServers;
    private HttpOutboundHandler handler;
    private HttpRequestFilter filter;

    public HttpInboundHandler(List<String> proxyServers) {
        this.proxyServers = proxyServers;
        this.handler = new HttpOutboundHandlerHttpClient(proxyServers);
        this.filter = new HttpInputHeaderFilter();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            String uri = fullHttpRequest.uri();
            logger.info("接收到的请求url为{}", uri);

            filter.filter(fullHttpRequest, ctx);
            handler.handle(fullHttpRequest, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
