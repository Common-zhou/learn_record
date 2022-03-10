package com.zhou.gc;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * 生成垃圾
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/03/10 09:07
 */
public class GCLogAnalysis {
  private static Random random = new Random();

  public static void main(String[] args) {
    // 当前毫秒时间戳
    long startTime = System.currentTimeMillis();
    // 结束时间戳
    long endTime = TimeUnit.SECONDS.toMillis(1) + startTime;

    int cacheSize = 2000;
    Object[] cache = new Object[cacheSize];

    LongAdder increment = new LongAdder();

    // 如果小于了当前时间
    while (System.currentTimeMillis() < endTime) {

      Object garbage = generateGarbage(100 * 1024);
      increment.increment();
      int index = random.nextInt(cacheSize * 2);
      if (index < cacheSize) {
        // 说明需要放入
        cache[index] = garbage;
      }

    }

    System.out.println(String.format("总共创建了 %s 个对象", increment.intValue()));

  }

  /**
   * 生成垃圾
   *
   * @param maxSize 指定最大size
   * @return
   */
  private static Object generateGarbage(int maxSize) {
    int randomSize = random.nextInt(maxSize);
    int type = randomSize % 4;

    Object res = null;
    switch (type) {
      case 0:
        res = new int[randomSize];
        break;
      case 1:
        res = new byte[randomSize];
        break;
      case 2:
        res = new double[randomSize];
        break;
      default:
        StringBuilder builder = new StringBuilder();
        String randomString = "randomString-Anything";
        while (builder.length() < randomSize) {
          builder.append(randomString);
          builder.append(maxSize);
          builder.append(randomSize);
        }
        res = builder.toString();
        break;
    }
    return res;
  }
}
