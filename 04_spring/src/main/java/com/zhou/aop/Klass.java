package com.zhou.aop;

import com.zhou.annotation.MyCache;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/04/15 23:50
 */
@Component
public class Klass {

  @MyCache
  public String ding(String user) {
    System.out.println("Kclass dingdingding....." + user);
    return "hello " + user;
  }
}
