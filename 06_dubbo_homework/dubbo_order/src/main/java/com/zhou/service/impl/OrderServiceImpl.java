package com.zhou.service.impl;

import com.zhou.mapper.OrderMapper;
import com.zhou.model.AccountDto;
import com.zhou.model.InventoryDto;
import com.zhou.model.Order;
import com.zhou.service.AccountService;
import com.zhou.service.InventoryService;
import com.zhou.service.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhoubing
 * @date 2022-05-29 20:14
 */
@Service
public class OrderServiceImpl implements OrderService {

    @DubboReference
    private AccountService accountService;

    @DubboReference
    private InventoryService inventoryService;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public boolean order(BigDecimal costMoney, int count) {
        return false;
    }


    @Override
    public String orderPay(BigDecimal money, Integer count) {
        Order order = builderOrder(money, count);
        int affectRows = orderMapper.save(order);

        accountService.updateAccount(buildAccountDto(money));

        inventoryService.updateInventory(buildInventoryDto(count));

        return "success";
    }

    private InventoryDto buildInventoryDto(Integer count) {
        return InventoryDto.builder().id(110).count(count).updateTime(new Date()).build();
    }

    private AccountDto buildAccountDto(BigDecimal money) {

        AccountDto accountDto = AccountDto.builder().id(1).balance(money).updateTime(new Date()).build();

        return accountDto;
    }

    private Order builderOrder(BigDecimal money, Integer count) {
        // 目前用户id 和商品id 只有一个
        Order order = Order.builder().accountId(1).inventoryId(110)
                .totalAmount(money).count(count).status(Order.OrderStatus.UNPAID.name()).createTime(new Date()).updateTime(new Date()).build();
        return order;
    }
}
