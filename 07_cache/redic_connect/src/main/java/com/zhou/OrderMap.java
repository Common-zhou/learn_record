package com.zhou;

import com.zhou.model.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * 懒得使用数据库。使用map来代替一下
 *
 * @author zhoubing
 * @date 2022-06-12 22:52
 */
public class OrderMap {
    private static final Map<String, Order> orderMap = new HashMap<>();

    public static Order getOrder(String id) {
        return orderMap.get(id);
    }

    public static Order addOrder(Order order) {
        return orderMap.put(order.getId(), order);
    }

    public static Order updateOrder(Order order) {
        return orderMap.put(order.getId(), order);
    }

}
