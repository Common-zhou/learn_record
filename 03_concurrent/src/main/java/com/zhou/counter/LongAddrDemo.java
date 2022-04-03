package com.zhou.counter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author zhoubing
 * @version 1.0.0
 */
public class LongAddrDemo {
  public static void main(String[] args) throws InterruptedException {
    LongAdder longAdder = new LongAdder();

    int threadNum = 100;
    CountDownLatch countDownLatch = new CountDownLatch(threadNum);

    for (int i = 0; i < threadNum; i++) {
      new Thread(() -> {
        for (int j = 0; j < 10000; j++) {
          longAdder.increment();
        }
        countDownLatch.countDown();
      }).start();
    }
    countDownLatch.await();

    System.out.println(longAdder.longValue());

  }
}
