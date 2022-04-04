package com.zhou.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhoubing
 * @date 2022-04-04 14:28
 */
public class LockSupportTest {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("unpark main thread......");
            LockSupport.unpark(mainThread);
        }).start();

        LockSupport.park();

        System.out.println("hello world");
    }
}
