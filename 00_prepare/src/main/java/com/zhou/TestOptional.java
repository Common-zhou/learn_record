package com.zhou;

import java.util.Optional;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/05/12 11:37
 */
public class TestOptional {
  public static void main(String[] args) {
    //Object orElse = Optional.of(null).orElse("hello world");
    //
    //System.out.println(orElse);

    Object helloWorld = Optional.ofNullable(null).orElse("hello world");
    System.out.println(helloWorld);

  }
}
