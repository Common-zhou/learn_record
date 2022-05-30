package com.zhou.service.impl;

import com.zhou.mapper.AccountMapper;
import com.zhou.model.AccountDto;
import com.zhou.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhoubing
 * @date 2022-05-28 22:09
 */
@Slf4j
@DubboService
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirmAccount", cancelMethod = "cancelAccount")
    public boolean updateAccount(AccountDto dto) {
        int affectRow = accountMapper.update(dto);
        return affectRow == 1;
    }

    public boolean confirmAccount(AccountDto dto) {
        log.info("==========confirm method===========");
        int affectRow = accountMapper.confirm(dto);
        return affectRow == 1;
    }

    public boolean cancelAccount(AccountDto dto) {
        int affectRow = accountMapper.cancel(dto);
        return affectRow == 1;
    }

    @Override
    public boolean updateAccountWithException(AccountDto dto) {
        throw new RuntimeException("更新库存失败");
    }
}
