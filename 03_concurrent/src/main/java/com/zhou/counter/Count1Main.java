package com.zhou.counter;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * NonSyncCounter 没有线程安全保证。
 * SyncCounter 是线程安全的。 利用的是锁 🔒
 * AtomicCounter 是线程安全的。 利用的是AtomicInteger
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/04/02 15:16
 */
public class Count1Main {
  private static final int SINGLE_THREAD_INCREMENT_COUNT = 10000;
  //private static Counter counter = new NonSyncCounter();
  //private static Counter counter = new SyncCounter();
  //private static Counter counter = new AtomicCounter();
  private static Counter counter = new LockCounter();

  private static CountDownLatch countDownLatch;


  static class IncrementCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
      System.out.println(Thread.currentThread().getName() + " begin add....");
      for (int i = 0; i < SINGLE_THREAD_INCREMENT_COUNT; i++) {
        counter.increment();
      }
      System.out.println(Thread.currentThread().getName() + " end add....");
      countDownLatch.countDown();
      return "this is callable increment.";
    }
  }


  static class IncrementRunnable implements Runnable {
    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + " begin add....");
      for (int i = 0; i < SINGLE_THREAD_INCREMENT_COUNT; i++) {
        counter.increment();
      }
      System.out.println(Thread.currentThread().getName() + " end add....");
      countDownLatch.countDown();

    }
  }

  public static void main(String[] args) throws InterruptedException {
    countDownLatch = new CountDownLatch(3);


    Thread t1 = new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + " begin add....");
      for (int i = 0; i < SINGLE_THREAD_INCREMENT_COUNT; i++) {
        counter.increment();
      }
      System.out.println(Thread.currentThread().getName() + " end add....");
      countDownLatch.countDown();

    });

    Thread t2 = new Thread(new IncrementRunnable());

    FutureTask<String> futureTask = new FutureTask<>(new IncrementCallable());
    Thread t3 = new Thread(futureTask);

    t1.start();
    t2.start();
    t3.start();

    countDownLatch.await();

    try {
      String s = futureTask.get();
      System.out.println(s);
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    System.out.println(counter.getCount());

  }
}
