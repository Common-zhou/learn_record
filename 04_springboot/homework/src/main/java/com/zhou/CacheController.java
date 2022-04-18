package com.zhou;

import com.zhou.anno.MyCache;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoubing
 * @date 2022-04-18 23:45
 */
@RestController
public class CacheController {

    @MyCache(60)
    @RequestMapping("/method1")
    public String method1() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("mm:DD:ss");

        return dateFormat.format(new Date());
    }

    @RequestMapping("/method2")
    public String method2() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("mm-DD-ss");

        return dateFormat.format(new Date());
    }
}
