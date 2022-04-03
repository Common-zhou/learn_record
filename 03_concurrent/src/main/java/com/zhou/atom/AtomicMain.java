package com.zhou.atom;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoubing
 * @date 2022-04-03 22:06
 */
public class AtomicMain {
    public static void main(String[] args) throws InterruptedException {
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        AtomicInteger atomicInteger = new AtomicInteger(0);

        for (int i = 0; i < threadSize; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    atomicInteger.incrementAndGet();
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println(atomicInteger.get());


    }
}
