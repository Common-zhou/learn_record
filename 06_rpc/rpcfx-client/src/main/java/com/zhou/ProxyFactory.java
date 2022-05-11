package com.zhou;

import com.zhou.proxy.CglibProxy;
import com.zhou.proxy.InvocationProxy;

/**
 * @author zhoubing
 * @date 2022-05-11 08:03
 */
public class ProxyFactory {
    public enum ProxyEnum {
        AOP,
        DYNAMIC
    }

    public static <T> T createProxy(Class<?> serviceClass, String url, ProxyEnum proxyEnum) {
        if (proxyEnum == null || ProxyEnum.DYNAMIC.equals(proxyEnum)) {
            // 默认是动态代理
            return InvocationProxy.create(serviceClass, url);
        } else if (ProxyEnum.AOP.equals(proxyEnum)) {
            return CglibProxy.create(serviceClass, url);
        }
        return null;

    }
}
