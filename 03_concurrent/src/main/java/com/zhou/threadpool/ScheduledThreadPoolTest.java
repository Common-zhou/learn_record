package com.zhou.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2022-04-04 00:18
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        scheduledExecutorService.schedule(
            () -> System.out.println(Thread.currentThread().getName() + " has been triggerred......"), 1,
            TimeUnit.MINUTES);

        scheduledExecutorService.scheduleAtFixedRate(
            () -> System.out.println(Thread.currentThread().getName() + " has been triggerred[111]......"),
            100, 100,
            TimeUnit.MICROSECONDS);
    }
}
