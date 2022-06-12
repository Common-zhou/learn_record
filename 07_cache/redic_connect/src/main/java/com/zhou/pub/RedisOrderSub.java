package com.zhou.pub;

import com.alibaba.fastjson.JSON;
import com.zhou.OrderMap;
import com.zhou.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.zhou.constants.OrderConstants.ORDER_CHANNEL_KEY;

/**
 * redis 负责 order状态改变
 *
 * @author zhoubing
 * @date 2022-06-12 23:07
 */
@Configuration
public class RedisOrderSub {

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        // 官方推荐我们使用自定义的线程池或者使用TaskExecutor
        container.setTaskExecutor(executor());
        container.addMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
                Order order = JSON.parseObject(message.getBody(), Order.class);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                order.setUpdateTime(new Date());
                order.setStatus("SUCCESS");
                OrderMap.updateOrder(order);
                System.out.println("=======async update order success========");

                System.out.println(OrderMap.getOrder(order.getId()));

            }
        }, new ChannelTopic(ORDER_CHANNEL_KEY));
        return container;

    }

    public TaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2);
        executor.setQueueCapacity(100);
        executor.initialize();
        return executor;
    }

}
