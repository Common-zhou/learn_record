package com.zhou.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author zhoubing
 * @date 2022-03-30 21:22
 */
public interface HttpResponseFilter {
    void filter(final FullHttpResponse fullHttpResponse);
}
