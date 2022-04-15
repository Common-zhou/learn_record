package com.zhou;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/04/15 23:52
 */
@Component
@Aspect
public class AnnoAop {
  @Pointcut(value = "@annotation(com.zhou.annotation.MyCache)")
  public void pointCut() {
  }

  @Around("pointCut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("========>AnnoAop around begin student ding ");
    Object proceed = joinPoint.proceed();
    System.out.println("========>AnnoAop around after student ding ");

    return proceed;
  }
}
