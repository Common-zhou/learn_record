package com.zhou.controller;

import com.zhou.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author zhoubing
 * @date 2022-05-28 21:26
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order")
    public String order(@RequestParam("money") BigDecimal money, @RequestParam("count") Integer count) {
        String result = orderService.orderPay(money, count);
        return "Ok";
    }


    @RequestMapping("/order_pay_exception")
    public String orderPayException(@RequestParam("money") BigDecimal money, @RequestParam("count") Integer count) {
        String result = orderService.orderPayException(money, count);
        return "Ok";
    }

}
