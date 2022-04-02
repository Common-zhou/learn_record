package com.zhou.counter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/04/02 15:31
 */
public class AtomicCounter implements Counter {
  private AtomicInteger atomicInteger = new AtomicInteger();

  @Override
  public void increment() {
    atomicInteger.incrementAndGet();
  }

  @Override
  public int getCount() {
    return atomicInteger.get();
  }
}
