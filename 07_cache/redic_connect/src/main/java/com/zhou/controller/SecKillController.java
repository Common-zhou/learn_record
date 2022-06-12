package com.zhou.controller;

import com.zhou.service.MallSecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoubing
 * @date 2022-06-12 15:41
 */
@RestController
public class SecKillController {
    @Autowired
    private MallSecKillService mallSecKillService;

    @GetMapping("/seckill")
    public int seckill(@RequestParam("id") int productId, @RequestParam("number") int number) {
        return mallSecKillService.seckill(productId, number);
    }
}
