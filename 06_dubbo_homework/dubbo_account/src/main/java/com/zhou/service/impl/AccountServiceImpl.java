package com.zhou.service.impl;

import com.zhou.mapper.AccountMapper;
import com.zhou.model.AccountDto;
import com.zhou.service.AccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

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
        AccountDto byId = accountMapper.findById(1);
        System.out.println(byId);

        return false;
    }

    @Override
    public String orderPay(BigDecimal money, Integer count) {


        return null;
    }
}
