package com.zhou.aop;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.zhou.anno.MyCache;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author zhoubing
 * @date 2022-04-18 23:53
 */
@Component
@Aspect
public class MyCacheAop {
    Map<Class, Cache<String, String>> cacheMap = new HashMap<>();

    @Pointcut(value = "@annotation(com.zhou.anno.MyCache)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        final String key = joinPoint.getSignature().toLongString();

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        final Class aClass = method.getDeclaringClass();
        Cache<String, String> cache = cacheMap.get(aClass);

        MyCache annotation = method.getAnnotation(MyCache.class);

        final int cacheS = annotation.value();

        if (cache == null) {
            synchronized (cacheMap) {
                if (cache == null) {
                    cache = CacheBuilder.newBuilder().expireAfterWrite(cacheS, TimeUnit.SECONDS).build();
                    cacheMap.put(aClass, cache);
                }
            }
        }

        final String value = cache.getIfPresent(key);
        if (value != null) {
            System.out.println("use cache ......");
            return value;
        }

        Object proceed = joinPoint.proceed();
        cache.put(key, proceed.toString());

        return proceed;
    }
}
