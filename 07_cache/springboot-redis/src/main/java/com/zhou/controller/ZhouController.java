package com.zhou.controller;

import com.zhou.ZhouUser;
import com.zhou.service.ZhouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * @param id
     * @return
     * @url http://localhost:8080/user/get?id=1
     */
    @GetMapping("/user/get")
    @Cacheable(cacheNames = "zhouCache", key = "#id")
    public ZhouUser findById(@RequestParam("id") int id) {
        System.out.println("id======" + id);
        return zhouService.findById(id);
    }


    /**
     * @return
     * @url http://localhost:8080/user/add?username=zhangsan&password=zhang&salt=salt
     */
    @GetMapping("/user/add")
    public int addUser(ZhouUser zhouUser) {
        return zhouService.insertUser(zhouUser);
    }

    /**
     * @return
     * @url http://localhost:8080/user/update?id=4&username=zhoubing&password=zhou&salt=saltzhou
     */
    @GetMapping("/user/update")
    public int updateUser(ZhouUser zhouUser) {
        return zhouService.updateUser(zhouUser);
    }
}
