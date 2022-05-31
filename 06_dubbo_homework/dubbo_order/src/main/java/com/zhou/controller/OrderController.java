package com.zhou.controller;

import com.zhou.service.OrderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author zhoubing
 * @date 2022-05-28 21:26
 */
@Api(value = "订单Controller", tags = {"订单访问接口"})
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "正常下单")
    @ApiResponses(value = {@ApiResponse(code = 1000, message = "成功"), @ApiResponse(code = 1001, message = "失败")})
    @GetMapping("/order")
    public String order(@ApiParam("钱数") @RequestParam("money") BigDecimal money,
                        @ApiParam("下单数目") @RequestParam("count") Integer count) {
        String result = orderService.orderPay(money, count);
        return "Ok";
    }


    @ApiOperation(value = "扣除账户余额异常")
    @ApiResponses(value = {@ApiResponse(code = 1000, message = "成功"), @ApiResponse(code = 1001, message = "失败")})
    @GetMapping("/order_pay_exception")
    public String orderPayException(@ApiParam("钱数") @RequestParam("money") BigDecimal money,
                                    @ApiParam("下单数目") @RequestParam("count") Integer count) {
        String result = orderService.orderPayException(money, count);
        return "Ok";
    }

}
