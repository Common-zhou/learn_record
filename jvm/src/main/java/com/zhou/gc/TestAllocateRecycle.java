package com.zhou.gc;

import java.io.IOException;

/**
 * 练习3.6 内存分配与回收策略
 *
 * @author zhoubing
 * @date 2021-08-28 16:40
 */
public class TestAllocateRecycle {

    private static final int _1MB = 1024 * 1024;

    /**
     * 3.6.1  对象优先在Eden分配
     * -vm 虚拟机参数如下
     * -XX:+PrintGCDetails  发生垃圾回收行为时打印内存回收日志，并且在进程退出的时候输出当前的内存各区域分配情况
     * -Xms20M -Xmx20M -Xmn10M  意思分别为：限制初始Java堆20M、最大堆20M、新生代10M,剩下10M分给老年代
     * -XX:SurvivorRatio=8 Eden区:一个Survivor区=8:1
     * <p>
     * -XX:SurvivorRatio=8  -XX:+PrintGCDetails  -Xms20M -Xmx20M -Xmn10M
     */
    public static void testAllocate1() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];

        /**
         * 1.第一次GC解读
         * 当新生代内存不足时，会首先进行一个Minor GC GC期间，发现三个2M的对象无法放入Survivor区，所以通过分配担保机制提前转移至老年代
         * 所以有了  [PSYoungGen: 6279K->840K(9216K)] 这个现象
         * 但总内存 6279K->4944K(19456K)  几乎无变化，因为三个对象都被引用，无法完成回收。
         *
         * 2.最终内存空间解读
         * 2M*3个对象在老年代。6M对象分配到新生代中。
         * PSYoungGen      total 9216K, used 7305K      (6144)
         * ParOldGen       total 10240K, used 4104K    (3*2048=6144)
         * 与预想的不一致。TODO  需要解决为什么是这样的。
         *
         * [GC (Allocation Failure) [PSYoungGen: 6279K->840K(9216K)] 6279K->4944K(19456K), 0.0014317 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * Heap
         *  PSYoungGen      total 9216K, used 7305K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
         *   eden space 8192K, 78% used [0x00000000ff600000,0x00000000ffc505d8,0x00000000ffe00000)
         *   from space 1024K, 82% used [0x00000000ffe00000,0x00000000ffed2020,0x00000000fff00000)
         *   to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
         *  ParOldGen       total 10240K, used 4104K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
         *   object space 10240K, 40% used [0x00000000fec00000,0x00000000ff002020,0x00000000ff600000)
         *  Metaspace       used 3485K, capacity 4498K, committed 4864K, reserved 1056768K
         *   class space    used 387K, capacity 390K, committed 512K, reserved 1048576K
         *
         * Process finished with exit code 0
         */
    }

    /**
     * 3.6.2 大对象直接分配到老年代
     * -XX:+UseParNewGC
     * -XX:PretenureSizeThreshold=3145728  设置多大的阈值直接进老年代。但必须加上以上的参数。
     * -verbose:gc -XX:SurvivorRatio=8  -XX:+PrintGCDetails  -Xms20M -Xmx20M -Xmn10M -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC
     * 因为1.8之后的垃圾收集器 Parallel Scavenge + Parallel Old  设置该参数无效
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation = new byte[4 * _1MB];
    }

    /**
     * 长期存活的直接进入老年代
     * -XX:MaxTenuringThreshold=1
     * 第一次Minor GC时，allocation1的age变为1.
     * 第二次Minor GC时，大于阈值 直接进入老年代
     */
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;

        allocation4 = new byte[4 * _1MB];
        //allocation5 = new byte[4 * _1MB];
    }

    public static void testTenuringThreshold2() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];
        // allocation1+allocation2 大于survivor空间一半

        allocation3 = new byte[4 * _1MB];

        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }

    public static void main(String[] args) throws IOException {
        //testAllocate1();
        //testPretenureSizeThreshold();
        //testTenuringThreshold();
        testTenuringThreshold2();
        System.in.read();
    }
}
