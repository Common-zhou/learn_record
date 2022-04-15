package com.zhou.aop;

import com.zhou.annotation.MyCache;
import org.springframework.stereotype.Component;

/**
 * @author zhoubing
 * @since 2022/04/15 23:59
 */
@Component
public class ISchoolImpl implements ISchool {
  @MyCache
  @Override
  public void study() {
    System.out.println("School study......");
  }
}
