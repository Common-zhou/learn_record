package com.zhou.homework1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zhoubing
 * @date 2022-04-04 15:49
 */
public class CyclicBarrierMethod implements CalFib {

    CyclicBarrier cyclicBarrier = new CyclicBarrier(1, () -> {
        System.out.println("===========");

        System.out.println(this.getValue());

    });

    private Integer result = null;

    @Override
    public void sum(int num) {
        result = fibo(num);
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getValue() {
        return result;
    }

    public CyclicBarrier getContext() {
        return cyclicBarrier;
    }
}
