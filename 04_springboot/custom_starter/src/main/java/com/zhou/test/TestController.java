package com.zhou.test;

import com.zhou.Klass;
import com.zhou.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoubing
 * @date 2022-04-17 23:45
 */
@RestController
public class TestController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/zhou")
    public String testMethod() {
        Klass klass = applicationContext.getBean(Klass.class);
        System.out.println(klass);

        Student student = applicationContext.getBean(Student.class);
        System.out.println(student);

        return "zhouzhou";
    }
}
