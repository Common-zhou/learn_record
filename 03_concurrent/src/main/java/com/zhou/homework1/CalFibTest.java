package com.zhou.homework1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhoubing
 * @date 2022-04-04 15:27
 */
public class CalFibTest {

    private static final int fibNum = 45;
    private static final int rightResult = 1836311903;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CalFib fib = new NoLockMethod();
        CalFib fib2 = new CountDownMethod();
        CalFib fib3 = new SemaphoreMethod();
        CalFib fib4 = new SynchronizedMethod();
        CalFib fib5 = new CyclicBarrierMethod();


        //calculateAndCheckValue(fib4);
        //
        //checkCyclicBarrier(fib5);

        useFutureTask();

    }

    private static void useFutureTask() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            CalFib fib = new DefaultFib();
            fib.sum(fibNum);
            return fib.getValue();
        });
        Thread thread = new Thread(futureTask);
        thread.start();

        Integer value = futureTask.get();
        if (value != rightResult) {
            throw new RuntimeException(String.format("answer is not right.[expect=%s, actual=%s]", rightResult, value));
        } else {
            System.out.println("future task test passed！");
        }
    }

    private static void checkCyclicBarrier(CalFib fib) {
        new Thread(() -> {
            fib.sum(fibNum);
        }).start();

    }

    private static void calculateAndCheckValue(CalFib fib) throws InterruptedException {

        new Thread(() -> {
            fib.sum(fibNum);
        }).start();

        int value = fib.getValue();

        if (value != rightResult) {
            throw new RuntimeException(String.format("answer is not right.[expect=%s, actual=%s]", rightResult, value));
        } else {
            System.out.println(fib + " test passed！");
        }
    }
}
