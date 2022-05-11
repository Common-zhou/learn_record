package com.zhou.service.impl;

import com.zhou.model.User;
import com.zhou.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Date;

/**
 * @author zhoubing
 * @date 2022-05-11 08:51
 */
@DubboService(version = "1.0.0")
public class UserServiceImpl implements UserService {
    @Override
    public User findById(int id) {
        return new User(id, "common-zhou" + System.currentTimeMillis(), new Date());
    }
}
