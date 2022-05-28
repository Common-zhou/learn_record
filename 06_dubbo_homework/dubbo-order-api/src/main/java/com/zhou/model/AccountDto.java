package com.zhou.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhoubing
 * @date 2022-05-28 22:07
 */
@Data
public class AccountDto implements Serializable {
    private int id;
    private String name;
    private String email;
    private BigDecimal balance;
    private BigDecimal freezeAmount;

    private Date createTime;
    private Date updateTime;
}

