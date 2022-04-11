package com.zhou;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author zhoubing
 * @date 2022-04-11 23:03
 */
@Aspect
public class Aop1 {
    @Pointcut(value = "execution(* com.zhou.aop.Student.ding(..))")
    public void point() {
    }

    @Before(value = "point()")
    public void before() {
        System.out.println("========>begin student ding... //2");
    }

    @AfterReturning(value = "point()")
    public void afterReturning() {
        System.out.println("========>after returning... ");
    }

    @Around("point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("========>around begin student ding //1");
        joinPoint.proceed();
        System.out.println("========>around after student ding //3");

    }


}
