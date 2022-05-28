package com.zhou.client;

import com.zhou.ProxyFactory;
import com.zhou.api.model.Order;
import com.zhou.api.model.User;
import com.zhou.api.service.ErrorService;
import com.zhou.api.service.OrderService;
import com.zhou.api.service.UserService;

/**
 * @author zhoubing
 * @date 2022-05-09 00:30
 */
public class MainStarter {
    public static void main(String[] args) {

        ProxyFactory.ProxyEnum proxyEnum = ProxyFactory.ProxyEnum.DYNAMIC;

        String url = "http://localhost:8088";

        UserService userService = ProxyFactory.createProxy(UserService.class, url, proxyEnum);

        User byId = userService.findById(1);
        System.out.println(byId);


        OrderService orderService = ProxyFactory.createProxy(OrderService.class, url, proxyEnum);
        Order order = orderService.createOrder(10086);
        System.out.println(order);


        // 这个调用会报错。因为对面没有实现。
        ErrorService errorService = ProxyFactory.createProxy(ErrorService.class, url, proxyEnum);
        errorService.invoke();


    }
}