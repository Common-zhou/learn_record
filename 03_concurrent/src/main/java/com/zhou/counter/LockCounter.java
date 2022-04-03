package com.zhou.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoubing
 * @version 1.0.0
 */
public class LockCounter implements Counter {
  private int count = 0;
  private Lock lock = new ReentrantLock(false);

  @Override
  public void increment() {
    lock.lock();
    try {
      count++;
    } finally {
      lock.unlock();
    }
  }

  @Override
  public int getCount() {
    return count;
  }
}
