package com.zhou.bytecode;

/**
 * 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，
 * 此文件内 容是一个 Hello.class 文件所有字节(x=255-x)处理后的文件。文件群里提供。
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/03/06 22:41
 */
public class Hello {
  private void hello() {
    System.out.println("this is from common-zhou compile");
  }

  private void hello(String name) {
    System.out.println("this is from common-zhou compile with parameter " + name);
  }
}
