package com.zhou.api.netty;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zhoubing
 * @date 2022-05-19 08:20
 */
public class RpcDecoder extends ByteToMessageDecoder {

    private Class<?> target;

    public RpcDecoder(Class<?> target) {
        this.target = target;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("decode----------");

        if (in.readableBytes() < 4) {
            // 因为我们第一个字节是长度，所以不满int 不接受请求
            return;
        }

        in.markReaderIndex();
        int dataLength = in.readInt();
        if (in.readableBytes() < dataLength) {
            // 读到的消息体不够。我们暂时等一等 并且把请求先换源
            in.resetReaderIndex();
            return;
        }

        byte[] data = new byte[dataLength];
        in.readBytes(data);

        Object obj = JSON.parseObject(data, target);
        out.add(obj);

    }
}
