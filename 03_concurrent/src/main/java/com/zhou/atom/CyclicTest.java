package com.zhou.atom;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2022-04-03 22:12
 */
public class CyclicTest {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);


        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) {
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + " begin work......");

                    Random random = new Random(System.currentTimeMillis());
                    int waitS = random.nextInt(10000);

                    try {
                        TimeUnit.MILLISECONDS.sleep(waitS);
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                    System.out.printf("%s end work.[time=%s]%n", Thread.currentThread().getName(), waitS);
                }).start();
            }

            System.out.println("one group has triggered....");
            TimeUnit.SECONDS.sleep(2);
        }

    }
}
