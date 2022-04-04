package com.zhou.homework1;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhoubing
 * @date 2022-04-04 15:31
 */
public class CountDownMethod implements CalFib {

    private int num;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void sum(int num) {
        this.num = fibo(num);
        countDownLatch.countDown();
    }

    @Override
    public int getValue() {
        try {
            countDownLatch.await();
            return this.num;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
