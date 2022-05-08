package com.zhou.service;

import com.zhou.api.model.User;
import com.zhou.api.service.UserService;

import java.util.Date;

/**
 * @author zhoubing
 * @date 2022-05-09 00:20
 */
public class UserServiceImpl implements UserService {
    @Override
    public User findById(int id) {
        return new User(id, "common-zhou" + System.currentTimeMillis(), new Date());
    }
}
