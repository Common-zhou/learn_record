package com.zhou.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhou.api.common.RpcfxRequest;
import com.zhou.api.common.RpcfxResponse;
import com.zhou.api.service.UserService;
import com.zhou.service.UserServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        String className = rpcfxRequest.getServiceClass();
        String methodName = rpcfxRequest.getMethodName();

        RpcfxResponse response = new RpcfxResponse();

        Object bean = applicationContext.getBean(className);
        Method method = null;
        try {
            method = bean.getClass().getMethod(methodName, int.class);

            Object result = method.invoke(bean, rpcfxRequest.getParams());

            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(0);
            return response;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            response.setException(e);
            response.setStatus(100);
            return response;
        }


    }

    @Bean(name = "com.zhou.api.service.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }
}
