package com.zhou.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2022-04-04 00:06
 */
public class SingleThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
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

    }
}
