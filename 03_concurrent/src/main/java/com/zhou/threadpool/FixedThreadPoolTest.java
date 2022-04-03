package com.zhou.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2022-04-04 00:09
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                System.out.println(String.format("%s has began to handle request.", Thread.currentThread().getName()));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("%s has done to handle request.", Thread.currentThread().getName()));

            });
        }

        System.out.println("main thread has over......");
        executorService.shutdown();
        //executorService.shutdownNow();
    }
}
