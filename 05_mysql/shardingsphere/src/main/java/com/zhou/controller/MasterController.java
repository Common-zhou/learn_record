package com.zhou.controller;

import com.zhou.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoubing
 * @date 2022-04-30 13:18
 */
@RestController
public class MasterController {

    @Autowired
    private MasterService masterService;

    @RequestMapping("/query")
    public String query() {
        return masterService.query();
    }


    @RequestMapping("/insert")
    public String insert(String name) {
        return masterService.insert(name);
    }
}
