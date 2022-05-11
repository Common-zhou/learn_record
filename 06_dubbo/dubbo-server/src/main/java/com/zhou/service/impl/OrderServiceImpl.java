package com.zhou.service.impl;

import com.zhou.model.Order;
import com.zhou.service.OrderService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author zhoubing
 * @date 2022-05-11 08:50
 */
@DubboService(version = "1.0.0")
public class OrderServiceImpl implements OrderService {
    @Override
    public Order findById() {
        Order order = new Order();
        order.setId(110);
        order.setName(1526 + "=========>been changed.");
        return order;
    }
}
