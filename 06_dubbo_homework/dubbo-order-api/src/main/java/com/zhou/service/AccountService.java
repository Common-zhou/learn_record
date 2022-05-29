package com.zhou.service;

import com.zhou.model.AccountDto;

/**
 * @author zhoubing
 * @date 2022-05-28 22:05
 */
public interface AccountService {
    boolean updateAccount(AccountDto dto);

    boolean updateAccountWithException(AccountDto dto);
}
