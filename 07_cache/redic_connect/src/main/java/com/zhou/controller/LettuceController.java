package com.zhou.controller;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LettuceController {

    @Autowired
    private RedisClient redisClient;

    /**
     * http://localhost:8088/lettuce/get?id=111
     *
     * @param id
     * @return
     */
    @RequestMapping("/lettuce/get")
    public Object get(@RequestParam("id") String id) {
        try (StatefulRedisConnection<String, String> redisConnection = redisClient.connect()) {
            return redisConnection.sync().get(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "fail";
    }

    /**
     * http://localhost:8088/lettuce/set?id=111&value=12345
     *
     * @param id
     * @param value
     * @return
     */
    @RequestMapping("/lettuce/set")
    public String set(@RequestParam("id") String id, @RequestParam("value") String value) {
        try (StatefulRedisConnection<String, String> redisConnection = redisClient.connect()) {
            return redisConnection.sync().set(id, value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "fail";
    }
}
