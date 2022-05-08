package com.zhou.proxy;

import com.zhou.api.service.UserService;

import java.lang.reflect.Proxy;

/**
 * @author zhoubing
 * @date 2022-05-09 00:31
 */
public class UserProxy {
    public static UserService create() {
        return (UserService) Proxy.newProxyInstance(UserProxy.class.getClassLoader(), new Class[]{UserService.class}, new UserProxyHandler());
    }


}
