package com.zhou.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/04/19 10:14
 */
public class BeanTest2 {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Bean2 {
    private String name;
    private int age;
  }
}
