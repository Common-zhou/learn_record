package com.zhou.service.impl;

import com.zhou.mapper.OrderMapper;
import com.zhou.model.AccountDto;
import com.zhou.model.InventoryDto;
import com.zhou.model.Order;
import com.zhou.service.AccountService;
import com.zhou.service.InventoryService;
import com.zhou.service.PayService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhoubing
 * @date 2022-05-29 22:58
 */
@Service
public class PayServiceImpl implements PayService {

    @DubboReference
    private AccountService accountService;

    @DubboReference
    private InventoryService inventoryService;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public boolean payOrder(Order order) {
        accountService.updateAccount(buildAccountDto(order.getTotalAmount()));
        inventoryService.updateInventory(buildInventoryDto(order.getCount()));
        return true;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public boolean payOrderAccountException(Order order) {
        accountService.updateAccountWithException(buildAccountDto(order.getTotalAmount()));
        inventoryService.updateInventory(buildInventoryDto(order.getCount()));
        return true;
    }


    private InventoryDto buildInventoryDto(Integer count) {
        return InventoryDto.builder().id(110).count(count).updateTime(new Date()).build();
    }

    private AccountDto buildAccountDto(BigDecimal money) {

        AccountDto accountDto = AccountDto.builder().id(1).balance(money).updateTime(new Date()).build();

        return accountDto;
    }


    public void confirmOrderStatus(Order order) {
        System.out.println("confirm order...");
        updateOrderStatus(order, Order.OrderStatus.PAY_SUCCESS);
    }

    public void cancelOrderStatus(Order order) {
        System.out.println("cancel order...");
        updateOrderStatus(order, Order.OrderStatus.CANCEL);
    }

    private void updateOrderStatus(Order order, Order.OrderStatus paySuccess) {
        orderMapper.updateStatus(order.getId(), paySuccess.name());
    }
}
