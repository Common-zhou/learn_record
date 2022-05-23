package com.zhou.controller;

import com.zhou.api.common.RpcfxRequest;
import com.zhou.api.common.RpcfxResponse;
import com.zhou.api.service.OrderService;
import com.zhou.api.service.UserService;
import com.zhou.invoker.InvokeBySpring;
import com.zhou.service.OrderServiceImpl;
import com.zhou.service.UserServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoubing
 * @date 2022-05-09 00:20
 */
@RestController
public class ServerController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostMapping("/")
    public RpcfxResponse invoke(@RequestBody RpcfxRequest rpcfxRequest) {
        InvokeBySpring invoker = applicationContext.getBean(InvokeBySpring.class);
        return invoker.invoke(rpcfxRequest);
    }

    @Bean(name = "com.zhou.api.service.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean(name = "com.zhou.api.service.OrderService")
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}
