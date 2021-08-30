package com.zhou.oom;

import java.nio.ByteBuffer;

/**
 * VM Args：-XX:MaxDirectMemorySize=100m
 * 限制最大内存100m  会导致内存溢出  直接内存溢出
 *
 * @author zhoubing
 * @date 2021-08-30 23:21
 */
public class DirectOom {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocateDirect(128 * 1024 * 1024);

    }
}
