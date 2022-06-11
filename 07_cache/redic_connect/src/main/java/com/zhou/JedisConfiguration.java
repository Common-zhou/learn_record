package com.zhou;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author zhoubing
 * @date 2022-06-11 15:17
 */
@Configuration
public class JedisConfiguration {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;

    @Bean
    public JedisPool jedisPoolCus() {
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setJmxEnabled(false);
//        return new JedisPool(config, host, port);
        return new JedisPool(host, port);

    }

}
