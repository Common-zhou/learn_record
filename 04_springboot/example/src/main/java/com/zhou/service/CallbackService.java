package com.zhou.service;

import com.zhou.Klass;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * @author zhoubing
 * @date 2022-04-18 00:19
 */
@Service
public class CallbackService implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        if (applicationContext != null) {

            List<String> beanDefinitionNames = Arrays.asList(applicationContext.getBeanDefinitionNames());
            if (beanDefinitionNames.contains("klass")){
                Klass bean = applicationContext.getBean(Klass.class);
                System.out.println(bean);
            }else{
                System.out.println("==================there is no klass =================");
            }


        }
    }
}
