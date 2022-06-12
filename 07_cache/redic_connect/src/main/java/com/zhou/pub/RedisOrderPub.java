package com.zhou.pub;

import com.alibaba.fastjson.JSON;
import com.zhou.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import static com.zhou.constants.OrderConstants.ORDER_CHANNEL_KEY;

/**
 * @author zhoubing
 * @date 2022-06-12 22:34
 */
@Component
public class RedisOrderPub {

    @Autowired
    private JedisPool jedisPool;

    public void publishOrder(Order order) {
        try (Jedis jedis = jedisPool.getResource()) {
            String str = JSON.toJSONString(order);
            jedis.publish(ORDER_CHANNEL_KEY, str);
        }
    }

}
