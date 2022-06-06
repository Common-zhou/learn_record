package com.zhou.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoubing
 * @date 2022-06-06 23:17
 */
@RestController
public class UserController {
    @GetMapping("/hello/cache")
    @Cacheable(cacheNames = "cache", key = "#id")
    public String helloCache(@RequestParam("id") String id) {
        System.out.println("cache====================" + id);
        return String.valueOf(System.currentTimeMillis());
    }


    @GetMapping("/hello/nocache")
    public String helloNoCache(@RequestParam("name") String name) {
        System.out.println("NO !!! cache====================" + name);

        return String.valueOf(System.currentTimeMillis());
    }


}
