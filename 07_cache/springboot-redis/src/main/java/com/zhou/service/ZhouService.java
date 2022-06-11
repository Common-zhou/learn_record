package com.zhou.service;

import com.zhou.ZhouUser;

/**
 * @author zhoubing
 * @date 2022-06-06 23:47
 */
public interface ZhouService {
    ZhouUser findById(int id);

    int insertUser(ZhouUser zhouUser);

    ZhouUser updateUser(ZhouUser zhouUser);

    int deleteUser(int id);
}
