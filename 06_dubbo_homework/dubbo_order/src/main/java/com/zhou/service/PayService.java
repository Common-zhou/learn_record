package com.zhou.service;

import com.zhou.model.Order;

/**
 * @author zhoubing
 * @date 2022-05-29 22:58
 */
public interface PayService {
    boolean payOrder(Order order);

    boolean payOrderAccountException(Order order);
}
