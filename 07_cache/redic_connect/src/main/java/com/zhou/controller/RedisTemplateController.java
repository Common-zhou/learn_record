package com.zhou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 可参考以下文档使用
 * https://blog.csdn.net/lanfeng_lan/article/details/121152461
 *
 * @author zhoubing
 * @date 2022-06-11 15:29
 */
@RestController
public class RedisTemplateController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * http://localhost:8088/redis_template/get?id=111
     *
     * @param id
     * @return
     */
    @RequestMapping("/redis_template/get")
    public Object get(@RequestParam("id") String id) {
        return redisTemplate.opsForValue().get(id);
    }

    /**
     * http://localhost:8088/redis_template/set?id=111&value=12345
     *
     * @param id
     * @param value
     * @return
     */
    @RequestMapping("/redis_template/set")
    public String set(@RequestParam("id") String id, @RequestParam("value") String value) {
        redisTemplate.opsForValue().set(id, value);
        return "ok";
    }
}
