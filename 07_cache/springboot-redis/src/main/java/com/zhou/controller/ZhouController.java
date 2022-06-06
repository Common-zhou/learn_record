package com.zhou.controller;

import com.zhou.ZhouUser;
import com.zhou.service.ZhouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoubing
 * @date 2022-06-06 23:51
 */
@RestController
public class ZhouController {
    @Autowired
    private ZhouService zhouService;

    @GetMapping("/user/get")
    public ZhouUser findById(@RequestParam("id") int id) {
        return zhouService.findById(id);
    }
}
