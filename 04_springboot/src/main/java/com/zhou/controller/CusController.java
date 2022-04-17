package com.zhou.controller;

import com.zhou.school.Klass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 专门为自定义的configuration所准备的单测类。
 *
 * @author zhoubing
 * @date 2022-04-17 22:40
 */
@RestController
public class CusController {

    @Autowired
    private Klass klass;

    @RequestMapping("/test/cus")
    public String testCustom() {
        System.out.println(klass);

        return "hello cus";
    }
}
