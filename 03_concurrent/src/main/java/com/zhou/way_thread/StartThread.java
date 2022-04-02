package com.zhou.way_thread;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 */
public class StartThread {
  public static void main(String[] args) {
    CreateThreadWay1 createThreadWay1 = new CreateThreadWay1();

    createThreadWay1.start();

    System.out.println("this is main thread" + Thread.currentThread().getName());


    System.out.println("==================");

    Thread thread = new Thread(new CreateThreadWay2());
    thread.start();

  }
}
