package com.zhou.service;

import com.zhou.api.model.Order;
import com.zhou.api.service.OrderService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhoubing
 * @date 2022-05-09 22:07
 */
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Override
    public Order createOrder(int id) {
        log.info("createOrder get order={}", id);
        Order order = new Order();
        order.setId(id);
        order.setName(id + "=========>been changed.");
        return order;
    }
}
