package com.zhou;

import org.apache.commons.lang3.reflect.ConstructorUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhoubing
 * @since 2022/04/21 19:33
 */
public class PracticeTestRunner {

  public static void main(String[] args)
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException,
      ClassNotFoundException {
    List<String> needCheckClass = Arrays.asList("Practice1", "Practice2", "Practice3", "Practice3");

    for (String checkClass : needCheckClass) {
      String className = PracticeTestRunner.class.getPackage().getName() + "." + checkClass;

      System.out.println("has checked result from class " + className);
      Class<?> aClass = Class.forName(className);

      Question question = (Question) ConstructorUtils.invokeConstructor(aClass);
      question.checkResult();
    }
  }
}
