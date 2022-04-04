package com.zhou.homework1;

/**
 * @author zhoubing
 * @date 2022-04-04 15:42
 */
public class SynchronizedMethod implements CalFib {
    private Object object = new Object();
    private Integer result = null;

    @Override
    public void sum(int num) {
        synchronized (object) {
            result = fibo(num);
            object.notifyAll();
        }
    }

    @Override
    public int getValue() {
        synchronized (object) {
            while (result == null) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return result;
        }
    }
}
