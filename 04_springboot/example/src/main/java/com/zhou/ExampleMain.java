package com.zhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhoubing
 * @date 2022-04-18 00:15
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.zhou.service")
public class ExampleMain {

    public static void main(String[] args) {
        SpringApplication.run(ExampleMain.class);
    }
}
