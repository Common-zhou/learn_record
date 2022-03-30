package com.zhou.gateway.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author zhoubing
 * @date 2022-03-27 23:17
 */
public interface HttpOutboundHandler {
    void handle(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx);
}
