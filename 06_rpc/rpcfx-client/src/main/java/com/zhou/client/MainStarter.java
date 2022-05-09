package com.zhou.client;

import com.zhou.api.model.Order;
import com.zhou.api.model.User;
import com.zhou.api.service.ErrorService;
import com.zhou.api.service.OrderService;
import com.zhou.api.service.UserService;
import com.zhou.proxy.InvocationProxy;

/**
 * @author zhoubing
 * @date 2022-05-09 00:30
 */
public class MainStarter {
    public static void main(String[] args) {
        UserService userService = InvocationProxy.create(UserService.class, "http://localhost:8080");

        User byId = userService.findById(1);
        System.out.println(byId);


        OrderService orderService = InvocationProxy.create(OrderService.class, "http://localhost:8080");
        Order order = orderService.createOrder(10086);
        System.out.println(order);

        ErrorService errorService = InvocationProxy.create(ErrorService.class, "http://localhost:8080");
        errorService.invoke();

    }
}
