package com.zhou.homework1;

import java.util.concurrent.Semaphore;

/**
 * @author zhoubing
 * @date 2022-04-04 15:31
 */
public class SemaphoreMethod implements CalFib {

    private int num;

    private Semaphore semaphore = new Semaphore(1);

    @Override
    public void sum(int num) {
        try {
            semaphore.acquire(1);
            this.num = fibo(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    @Override
    public int getValue() {

        try {
            semaphore.acquire(1);
            return this.num;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        return 0;
    }
}
