package com.zhou;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author zhoubing
 * @date 2022-06-11 17:01
 */
@Configuration
public class LettuceConfiguration {
    private StatefulRedisConnection<String, String> connection;

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;


    @Bean
    public RedisClient statefulRedisConnection() {
        RedisURI redisUri = RedisURI.builder()
                .withHost(host).withPort(port)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        return RedisClient.create(redisUri);
    }


}
