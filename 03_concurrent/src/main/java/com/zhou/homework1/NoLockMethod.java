package com.zhou.homework1;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2022-04-04 15:21
 */
public class NoLockMethod implements CalFib {
    private volatile Integer value = null;

    public void sum(int num) {
        value = fibo(num);
    }

    public int getValue() {
        while (value == null) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public static void main(String[] args) {
        NoLockMethod noLockMethod = new NoLockMethod();

        int fibNum = 45;
        int rightResult = 1836311903;

        new Thread(() -> noLockMethod.sum(fibNum)).start();
        System.out.println(noLockMethod.getValue());

        if (noLockMethod.getValue() != rightResult) {
            throw new RuntimeException("answer is not right");
        }
    }

}
