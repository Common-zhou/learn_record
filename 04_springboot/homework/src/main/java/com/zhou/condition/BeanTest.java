package com.zhou.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoubing
 * @since 2022/04/19 09:56
 */
@Configuration
public class BeanTest {
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Bean1 {
    private String name;
    private int age;
  }



  @Bean
  public BeanTest2.Bean2 bean2() {
    return new BeanTest2.Bean2("zhangsan", 111);
  }

  //@ConditionalOnBean(name = "bean2")
  @ConditionalOnClass(BeanTest2.Bean2.class)
  //@Bean
  public Bean1 bean1() {
    return new Bean1("zhangsan", 111);
  }

  @ConditionalOnMissingBean(BeanTest.Bean1.class)
  @Bean
  public Bean1 bean11() {
    return new Bean1("zhangsan", 111);
  }


}
