package com.zhou.client;

import com.zhou.api.model.User;
import com.zhou.api.service.UserService;
import com.zhou.proxy.UserProxy;

/**
 * @author zhoubing
 * @date 2022-05-09 00:30
 */
public class MainStarter {
    public static void main(String[] args) {
        UserService userService = UserProxy.create();

        User byId = userService.findById(1);
        System.out.println(byId);
    }
}
