package com.zhou.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author zhoubing
 * @date 2022-03-30 21:35
 */
public class HttpResponseHeaderFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse fullHttpResponse) {
        fullHttpResponse.headers().add("nio", "zhoubing");
    }
}
