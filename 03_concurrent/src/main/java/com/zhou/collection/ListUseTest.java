package com.zhou.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2022-04-04 16:49
 */
public class ListUseTest {
    public static void main(String[] args) throws InterruptedException {
        // 非线程安全的容器

        //Collection<String> synchronizedCollection = new ArrayList<>();
        Collection<String> synchronizedCollection = Collections.synchronizedCollection(new ArrayList<>());
        //Collection<String> synchronizedCollection = new Vector<>();

        int threadNum = 1000;

        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        ExecutorService executorService = Executors.newFixedThreadPool(50);

        for (int i = 0; i < threadNum; i++) {
            executorService.submit(() -> {
                try {
                    synchronizedCollection.add(Thread.currentThread().getName());
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println(synchronizedCollection.size());

        executorService.shutdown();

    }
}
