package com.zhou.counter;

/**
 * @author zhoubing
 * @version 1.0.0
 */
public class NonSyncCounter implements Counter {
  private static int count = 0;

  public void increment() {
    count++;
  }

  public int getCount() {
    return count;
  }
}
