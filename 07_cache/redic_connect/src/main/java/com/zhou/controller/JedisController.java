package com.zhou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author zhoubing
 * @date 2022-06-11 15:29
 */
@RestController
public class JedisController {

    @Autowired
    private JedisPool jedisPool;

    /**
     * http://localhost:8088/jedis/get?id=111
     * @param id
     * @return
     */
    @RequestMapping("/jedis/get")
    public String get(@RequestParam("id") String id) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(id);
        }
    }

    /**
     * http://localhost:8088/jedis/set?id=111&value=12345
     * @param id
     * @param value
     * @return
     */
    @RequestMapping("/jedis/set")
    public String set(@RequestParam("id") String id, @RequestParam("value")String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.set(id, value);
        }
    }
}
