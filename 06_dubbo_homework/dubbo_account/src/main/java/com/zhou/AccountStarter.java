package com.zhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author zhoubing
 * @date 2022-05-28 22:30
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ImportResource({"classpath:applicationContext.xml"})
public class AccountStarter {
    public static void main(String[] args) {
        SpringApplication.run(AccountStarter.class);
    }

}
