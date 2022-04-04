package com.zhou;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2. 思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，
 * 拿到这个方法的返回值后，退出主线程？
 *
 * @author zhoubing
 * @date 2022-04-04 14:42
 */
public class Homework1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Homework1 homework1 = new Homework1();

        //homework1.method1();
        //homework1.method2();
        //homework1.method3();
        homework1.method4();
    }

    /**
     * 使用 FutureTask callable
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void method1() throws ExecutionException, InterruptedException {
        System.out.println("开始方法1......");

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(1);
                return "this is result1.";
            }
        };

        FutureTask<String> result = new FutureTask<>(callable);
        new Thread(result).start();

        System.out.println(result.get());

    }

    /**
     * 使用线程池的方法
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void method2() throws ExecutionException, InterruptedException {
        System.out.println("开始方法2.。。。。。");
        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(1);
                return "this is result2.";
            }
        });
        System.out.println(future.get());

        service.shutdown();
    }

    /**
     * 使用CountDownLatch 来完成
     *
     * @throws InterruptedException
     */
    public void method3() throws InterruptedException {
        System.out.println("开始方法3。。。。。。");

        CountDownLatch countDownLatch = new CountDownLatch(1);
        List<String> result = new ArrayList<>();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                result.add("this is method3 result");
                System.out.println("method3 sleep end......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            countDownLatch.countDown();
        }).start();
        countDownLatch.await();

        System.out.println(result);
        System.out.println("main thread end.");

    }

    public void method4() throws InterruptedException {
        System.out.println("开始方法4.。。。。。");
        //Semaphore semaphore = new Semaphore(1);
        AtomicInteger atomicInteger = new AtomicInteger();

        CopyOnWriteArrayList result = new CopyOnWriteArrayList();

        new Thread(() -> {
            //System.out.println("开始抢信号量");
            try {
                TimeUnit.SECONDS.sleep(2);
                //semaphore.acquire(1);
                System.out.println(Thread.currentThread().getName() + " has acquired semphore.");
                result.add("this is method4 result");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 要保证一定会set进去 否则会卡住
                atomicInteger.incrementAndGet();
                //semaphore.release();
            }
        }).start();

        while (atomicInteger.get() == 0) {
            System.out.println(" wait thread execute");
        }

        //semaphore.acquire(1);
        System.out.println("main thread has acquired semphore.");

        System.out.println(result);
    }
}
