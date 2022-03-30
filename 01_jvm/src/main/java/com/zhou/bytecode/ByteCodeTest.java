package com.zhou.bytecode;

/**
 * 自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后
 * 自己分析一下对应的字节码，有问题群里讨论。
 *
 * 这个是有四则运算的
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/03/06 22:09
 */
public class ByteCodeTest {
  public static void main(String[] args) {
    int i = 1;
    int j = 4;

    int sum = i + j;
    int sub = i - j;

    int mul = i * j;
    int div = i / j;

    //if_icmpne 比较大小
    if (sum == 5) {
      System.out.println(sub);
    } else {
      System.out.println(mul);
    }

    for (int k = 0; k > 3; k++) {
      int t = k;
    }

  }
}
