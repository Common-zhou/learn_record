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

    /**
     * 正常下单流程
     *
     * @param money 耗费钱数
     * @param count 下单数量
     * @return
     */
    String orderPay(BigDecimal money, Integer count);
}
