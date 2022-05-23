package com.zhou.netty;

import com.zhou.api.common.RpcfxRequest;
import com.zhou.api.common.RpcfxResponse;
import com.zhou.api.netty.RpcDecoder;
import com.zhou.api.netty.RpcEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * 请求的客户端
 */
public class NettyClient {
    private final String host;
    private final int port;
    private Channel channel;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Bootstrap start() {
        final EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("正在连接中");
                        ChannelPipeline pipeline = ch.pipeline();

                        pipeline.addLast(new RpcEncoder(RpcfxRequest.class));
                        pipeline.addLast(new RpcDecoder(RpcfxResponse.class));
                        pipeline.addLast(new ClientHandler());
                    }
                });

        return bootstrap;

    }


}
