package com.zhou.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author zhoubing
 * @date 2022-04-04 14:12
 */
public class LockDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(false);
        int threadNum = 100_00000;
        LockCounter lockCounter = new LockCounter();

        IntStream.range(0, threadNum).parallel().forEach((i) -> lockCounter.addAndGet());

        System.out.println(lockCounter.get());

    }
}
