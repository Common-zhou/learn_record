package com.zhou.controller;

import com.zhou.model.InventoryDto;
import com.zhou.service.AccountService;
import com.zhou.service.InventoryService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
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

    @DubboReference
    private AccountService accountService;

    @DubboReference
    private InventoryService inventoryService;

    @RequestMapping("/order")
    public String order(@RequestParam("money") BigDecimal money, @RequestParam("count") Integer count) {

        String result = accountService.orderPay(money, count);

        accountService.updateAccount(null);

        InventoryDto inventoryDto = inventoryService.findById(110);
        System.out.println(inventoryDto);

        return "Ok";
    }


    @GetMapping("/test1")
    public String order() {
        System.out.println("===============");
        return "Ok";
    }

}
