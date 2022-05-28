package com.zhou.service;

import java.math.BigDecimal;

/**
 * @author zhoubing
 * @date 2022-05-28 21:23
 */
public interface OrderService {
    /**
     * @param costMoney 花费钱数
     * @param count     数目
     * @return
     */
    boolean order(BigDecimal costMoney, int count);
}
