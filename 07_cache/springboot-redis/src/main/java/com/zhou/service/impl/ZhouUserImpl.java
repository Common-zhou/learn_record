package com.zhou.service.impl;

import com.zhou.ZhouUser;
import com.zhou.mapper.ZhouMapper;
import com.zhou.service.ZhouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhoubing
 * @date 2022-06-06 23:48
 */
@Service
public class ZhouUserImpl implements ZhouService {

    @Autowired
    private ZhouMapper zhouMapper;

    @Override
    public ZhouUser findById(int id) {
        return zhouMapper.findById(id);
    }

    @Override
    public int insertUser(ZhouUser zhouUser) {
        int affectUser = zhouMapper.insert(zhouUser);
        return zhouUser.getId();
    }

    @Override
    public int updateUser(ZhouUser zhouUser) {
        int affectUser = zhouMapper.update(zhouUser);
        return zhouUser.getId();
    }
}
