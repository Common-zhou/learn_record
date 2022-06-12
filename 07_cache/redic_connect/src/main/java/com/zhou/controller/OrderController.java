package com.zhou.controller;

import com.zhou.OrderMap;
import com.zhou.model.Order;
import com.zhou.pub.RedisOrderPub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @author zhoubing
 * @date 2022-06-12 22:56
 */
@RestController
public class OrderController {

    @Autowired
    private RedisOrderPub orderPub;

    /**
     * 懒得写service 。直接写controller里面了
     *
     * @param money
     */
    @GetMapping("/order/add")
    public void addOrder(@RequestParam("money") Integer money) {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        Order order = new Order(id, "UNPAY", money, new Date(), new Date());

        OrderMap.addOrder(order);

        System.out.println("add order" + order);

        orderPub.publishOrder(order);
    }
}
