package com.zhou.gateway.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author zhoubing
 * @date 2022-03-27 23:07
 */
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    private String proxyServer;

    public HttpInboundInitializer(String proxyServer) {
        this.proxyServer = proxyServer;
    }

    //@Override
    //public void initChannel(SocketChannel ch) {
    //    ChannelPipeline p = ch.pipeline();
    //    // TODO 这里是在干啥？？？？
    //    p.addLast(new HttpServerCodec());
    //    p.addLast(new HttpObjectAggregator(1024 * 1024));
    //
    //    p.addLast(new HttpInboundHandler(this.proxyServer));
    //
    //}

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpServerCodec());
        //p.addLast(new HttpServerExpectContinueHandler());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        p.addLast(new HttpInboundHandler(this.proxyServer));
    }
}
