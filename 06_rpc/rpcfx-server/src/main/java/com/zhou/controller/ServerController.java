package com.zhou.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhou.api.common.RpcfxRequest;
import com.zhou.api.common.RpcfxResponse;
import com.zhou.api.service.OrderService;
import com.zhou.api.service.UserService;
import com.zhou.service.OrderServiceImpl;
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
import java.util.Arrays;

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

        try {
            Object bean = applicationContext.getBean(className);
            Method method = resolveMethod(bean, methodName);

            Object result = method.invoke(bean, rpcfxRequest.getParams());

            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(0);
            return response;
        } catch (IllegalAccessException | InvocationTargetException | BeansException e) {
            e.printStackTrace();
            response.setException(e.toString());
            response.setStatus(100);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.setException(e.toString());
            response.setStatus(100);
            return response;
        }


    }

    private Method resolveMethod(Object bean, String methodName) {
        return Arrays.stream(bean.getClass().getMethods()).filter(method -> method.getName().equals(methodName)).findFirst().get();
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
