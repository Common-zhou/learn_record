package com.zhou.gc;

/**
 * 测试循环引用。发现仍然会清理，因为jvm是采用的可达性分析算法来回收的。
 * @author zhoubing
 * @date 2021-08-28 09:53
 */
public class ReferenceCountingGC {
    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;

        //objA = null;
        //objB = null;
        System.gc();

    }
}
