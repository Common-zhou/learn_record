package com.zhou;

public class StaticDemo {

  private static int num = (int) (Math.random() * 100);

  public static int showDemo(int a) {
    System.out.println("static show demo: " + a);
    return a;
  }
}

