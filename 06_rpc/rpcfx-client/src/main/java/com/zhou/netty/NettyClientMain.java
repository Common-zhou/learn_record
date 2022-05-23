package com.zhou.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhou.api.common.RpcfxRequest;
import com.zhou.api.common.RpcfxResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.io.IOException;

/**
 * @author zhoubing
 * @date 2022-05-19 09:01
 */
public class NettyClientMain {

    private static Bootstrap bootstrap;

    static {
        NettyClient client = new NettyClient("localhost", 8088);
        bootstrap = client.start();
    }

    public static RpcfxResponse post(String url, RpcfxRequest request) {
        try {
            connect(bootstrap, request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new RpcfxResponse();
    }


    public static void main(String[] args) throws IOException {
        NettyClient client = new NettyClient("localhost", 8088);
        Bootstrap bootstrap = client.start();
    }

    private static void connect(Bootstrap bootstrap, RpcfxRequest request) throws InterruptedException {
        bootstrap.connect("localhost", 8088).addListener(future -> {
            System.out.println("future状态 :" + future.isSuccess());
            if (future.isSuccess()) {
                Channel channel = ((ChannelFuture) future).channel();
                channel.writeAndFlush(JSON.toJSONString(request, SerializerFeature.WriteClassName));
            }
        }).sync();
    }

}
