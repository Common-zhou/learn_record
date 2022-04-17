package com.zhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhoubing
 * @date 2022-04-17 22:23
 */
@EnableAutoConfiguration
//@ComponentScan
@ComponentScan(basePackages = "com.zhou.controller")
public class EnableAutoConfigurationMain {
    public static void main(String[] args) {
        SpringApplication.run(EnableAutoConfigurationMain.class);
    }
}
