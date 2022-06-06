package com.zhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.IOException;

/**
 * @author zhoubing
 * @date 2022-06-06 23:16
 */
@SpringBootApplication
@EnableCaching
public class MainStarter {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(MainStarter.class);

    }
}
