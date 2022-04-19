package com.zhou.condition;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/04/19 09:58
 */
public class BeanMain {
  public static void main(String[] args) {

    ApplicationContext context = new AnnotationConfigApplicationContext("com.zhou.condition");
    //BeanTest.Bean1 bean1 = (BeanTest.Bean1) context.getBean("bean1");
    //System.out.println(bean1);

    BeanTest.Bean1 bean11 = (BeanTest.Bean1) context.getBean("bean11");
    System.out.println(bean11);

  }
}
