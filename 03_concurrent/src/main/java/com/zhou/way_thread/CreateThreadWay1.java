package com.zhou.way_thread;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 */
public class CreateThreadWay1 extends Thread {
  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + "----- start......");

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(Thread.currentThread().getName() + "----- end......");


  }
}
