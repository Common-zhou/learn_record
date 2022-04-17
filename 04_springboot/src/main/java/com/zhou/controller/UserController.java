package com.zhou.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoubing
 * @date 2022-04-13 23:22
 */
@RestController
public class UserController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello world spring boot";
    }
}
