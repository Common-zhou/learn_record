package com.zhou.gc;

import java.lang.ref.WeakReference;

/**
 * 测试强引用、软引用、弱引用、虚引用（不测试）
 * 强引用：即使oom 也不会清理
 * 软引用：当内存不足时，会回收软引用。内存充足时，不会回收
 * 弱引用：碰上gc 就会回收
 * @author zhoubing
 * @date 2021-08-28 11:49
 */
public class ReferenceCollect {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {

        // 会oom
        //testStrong();

        byte[] _3M = new byte[3 * _1MB];

        //SoftReference<byte[]> reference = new SoftReference<>(_3M);
        WeakReference<byte[]> reference = new WeakReference<>(_3M);
        _3M = null;

        System.out.println(reference.get());

        System.gc();
        System.out.println("After gc......");
        System.out.println(reference.get());

        System.out.println("========");
        byte[] _4M;
        byte[] _4m2 = null;
        try {
            _4M = new byte[4 * _1MB];
            _4m2 = new byte[4 * _1MB];
        } catch (Throwable e) {
            System.out.println("After oom" + reference.get());
        }

        System.out.println(_4m2);

    }

    private static void testStrong() {
        byte[] _3M = new byte[3 * _1MB];
        byte[] _4M = new byte[4 * _1MB];
        byte[] _4M2 = new byte[4 * _1MB];
    }
}
