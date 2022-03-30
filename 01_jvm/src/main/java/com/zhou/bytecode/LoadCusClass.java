package com.zhou.bytecode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 加载编码过的class文件 就是当前的 255-x。
 * 这样编码过。将其反编码回来
 *
 * <pre>
 *   建议使用步骤：
 *      1.打包Hello.java
 *      2.运行DecodeClassCustom 将文件打包成 HelloCus
 *      3.运行本文件。 即可使用。
 *      实现了两种，一种是有参的反射调用，一种是无参数的反射调用
 * </pre>
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/03/06 23:02
 */
public class LoadCusClass extends ClassLoader {
  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    File file =
        new File("/Users/zhoubing/IdeaProjects/learn_record/jvm/src/main/java/com/zhou/bytecode/HelloCus.class");

    Long length = file.length();

    byte[] data = new byte[length.intValue()];
    try {
      FileInputStream fileInputStream = new FileInputStream(file);
      fileInputStream.read(data);
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < data.length; i++) {
      data[i] = (byte) (255 - data[i]);
    }
    return defineClass(name, data, 0, data.length);
  }

  public static void main(String[] args) {
    LoadCusClass loadCusClass = new LoadCusClass();
    Class<?> aClass = null;
    try {
      aClass = loadCusClass.findClass("com.zhou.bytecode.Hello");
      Object o = aClass.newInstance();

      Method helloMethod = aClass.getDeclaredMethod("hello");
      // 我写了private修饰符 如果不设置 用不了
      helloMethod.setAccessible(true);
      helloMethod.invoke(o);

      Method helloMethodWithParameter = aClass.getDeclaredMethod("hello", String.class);
      helloMethodWithParameter.setAccessible(true);
      helloMethodWithParameter.invoke(o, "zhouzhou");

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }


  }
}
