package com.zhou.confiugration;

import com.zhou.bean.Animal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoubing
 * @date 2022-04-11 23:36
 */
@Configuration
public class BeanConfiguration {
    @Bean
    public Animal animal4() {
        Animal animal = new Animal();
        animal.setName("bean");
        animal.setAge(8);
        return animal;
    }
}
