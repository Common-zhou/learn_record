package com.zhou.consumer;

import com.zhou.model.Order;
import com.zhou.model.User;
import com.zhou.service.OrderService;
import com.zhou.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author zhoubing
 * @date 2022-05-11 08:45
 */
@SpringBootApplication
public class DubboConsumer {


    @DubboReference(version = "1.0.0") //, url = "dubbo://127.0.0.1:12345")
    private UserService userService;

    @DubboReference(version = "1.0.0") //, url = "dubbo://127.0.0.1:12345")
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumer.class).close();
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            User user = userService.findById(1);
            System.out.println("find user id=1 from server: " + user.getName());
            Order order = orderService.findById();
            System.out.println(String.format("find order id=%s name=%s", order.getId(), order.getName()));
        };
    }
}
