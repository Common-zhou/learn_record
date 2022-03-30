package com.zhou.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author zhoubing
 * @date 2022-03-30 21:22
 */
public interface HttpRequestFilter {
    void filter(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx);
}
