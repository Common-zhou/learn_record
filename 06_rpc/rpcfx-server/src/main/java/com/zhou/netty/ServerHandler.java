package com.zhou.netty;

import com.zhou.api.common.RpcfxRequest;
import com.zhou.api.common.RpcfxResponse;
import com.zhou.holder.SpringHolder;
import com.zhou.invoker.InvokeBySpring;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.context.ApplicationContext;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    //接受client发送的消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ApplicationContext applicationContext = SpringHolder.getApplicationContext();

        InvokeBySpring invoker = applicationContext.getBean(InvokeBySpring.class);

        RpcfxRequest request = (RpcfxRequest) msg;
        RpcfxResponse response = invoker.invoke(request);
        System.out.println("接收到客户端信息:" + request.toString());
        //返回的数据结构
        ctx.writeAndFlush(response);
    }

    //通知处理器最后的channelRead()是当前批处理中的最后一条消息时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端接收数据完毕..");
        ctx.flush();
    }

    //读操作时捕获到异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    //客户端去和服务端连接成功时触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush("hello client");
    }
}
