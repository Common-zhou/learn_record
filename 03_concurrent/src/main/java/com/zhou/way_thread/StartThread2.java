package com.zhou.way_thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用thread的方式
 * @author zhoubing
 */
public class StartThread2 {
  public static void main(String[] args) {
    CreateThreadWay3 task = new CreateThreadWay3();
    FutureTask<String> futureTask = new FutureTask<>(task);
    Thread thread = new Thread(futureTask);

    thread.start();

    try {
      String s = futureTask.get();
      System.out.println("got call result:  " + s);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

  }
}
