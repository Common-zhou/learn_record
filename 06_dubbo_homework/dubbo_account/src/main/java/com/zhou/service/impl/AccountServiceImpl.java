package com.zhou.service.impl;

import com.zhou.mapper.AccountMapper;
import com.zhou.model.AccountDto;
import com.zhou.service.AccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhoubing
 * @date 2022-05-28 22:09
 */
@DubboService
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public boolean updateAccount(AccountDto dto) {
        int affectRow = accountMapper.update(dto);
        return affectRow == 1;
    }

    @Override
    public boolean updateAccountWithException(AccountDto dto) {
        throw new RuntimeException("更新库存失败");
    }
}
