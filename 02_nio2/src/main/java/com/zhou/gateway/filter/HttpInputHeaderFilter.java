package com.zhou.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author zhoubing
 * @date 2022-03-30 21:23
 */
public class HttpInputHeaderFilter implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        fullHttpRequest.headers().add("noi", "zhoubing");
    }
}
