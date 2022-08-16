package com.zhou;

import lombok.Setter;

/**
 * @author zhoubing
 * @since 2022/08/12 17:02
 */
@Setter
public class PrintDemo {
  private String prefix;

  private ADemo aDemo;

  public void sayHello(String name, int age) {
    System.out.println("name: " + name + " age: " + age);
  }

  public void print(ADemo aDemo) {
    System.out.println(prefix + " ==> " + aDemo);
  }



  public void print(String str, String clz) {
    System.out.println("str2a: " + str + " clz: " + clz);
  }

  public void print(String str, OgnlEnum ognlEnum) {
    System.out.println("enum: " + str + ":" + ognlEnum);
  }

  public void print(String str, ADemo a) {
    System.out.println("obj: " + str + ":" + a);
  }

  public void show(Class clz) {
    System.out.println(clz.getName());
  }


}
