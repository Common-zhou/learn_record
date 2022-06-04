package com.zhou.client;

import com.zhou.api.model.Order;
import com.zhou.api.service.OrderService;
import com.zhou.proxy.CglibProxy;

import java.util.Arrays;

/**
 * @author zhoubing
 * @date 2022-05-31 23:42
 */
public class NewStarter {
    public static void main(String[] args) {
        OrderService orderService = CglibProxy.create(OrderService.class,
                Arrays.asList("http://localhost:8080", "http://localhost:8082"));
        Order order = orderService.createOrder(1);
        System.out.println(order);
    }


}
