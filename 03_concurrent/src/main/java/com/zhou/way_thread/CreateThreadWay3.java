package com.zhou.way_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 */
public class CreateThreadWay3 implements Callable<String> {
  @Override
  public String call() throws Exception {
    System.out.println(Thread.currentThread().getName() + "====> start......");
    TimeUnit.SECONDS.sleep(2);

    return "线程C";
  }
}
