package com.zhou.counter;

/**
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/04/02 15:24
 */
public class SyncCounter implements Counter {
  private static int count = 0;

  public synchronized void increment() {
    count++;
  }

  public int getCount() {
    return count;
  }
}
