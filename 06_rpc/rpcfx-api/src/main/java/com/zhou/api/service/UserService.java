package com.zhou.api.service;

import com.zhou.api.model.User;

/**
 * @author zhoubing
 * @date 2022-05-09 00:12
 */
public interface UserService {
    User findById(int id);
}
