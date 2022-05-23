package com.zhou.invoker;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhou.api.common.Invoker;
import com.zhou.api.common.RpcfxRequest;
import com.zhou.api.common.RpcfxResponse;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author zhoubing
 * @date 2022-05-19 08:54
 */
@Component
public class InvokeBySpring implements Invoker, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public RpcfxResponse invoke(RpcfxRequest rpcfxRequest) {
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
}
