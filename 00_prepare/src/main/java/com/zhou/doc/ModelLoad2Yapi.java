package com.zhou.doc;

import java.nio.file.FileSystems;

/**
 * 主要用来获取model的成员变量，以及将其转换为yapi
 *
 * @author zhoubing
 * @since 2022/06/08 18:13
 */
public class ModelLoad2Yapi {
  public static void main(String[] args) {

    ModelLoad2Yapi modelLoad2Yapi = new ModelLoad2Yapi();

    Class<?> aClass = MUserBean.class;

    System.out.println(modelLoad2Yapi.getSimpleName(aClass.getName()));

    String userDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
    System.out.println(userDirectory);
    String basePath = String.format("%s/00_prepare/src/main/java", userDirectory);
    System.out.println(basePath);

  }

  /**
   * 获取. 的最后一个
   *
   * @param name
   * @return
   */
  public String getSimpleName(String name) {
    return name.substring(name.lastIndexOf(".") + 1);
  }
}