package com.zhou.configuration;

import com.zhou.Klass;
import com.zhou.Student;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoubing
 * @date 2022-04-17 23:36
 */
@Configuration
public class ZhouConfiguration {
    @Bean
    public Klass klass() {
        Klass klass = new Klass();
        klass.setName("zhou");
        klass.setNumber(20);
        return klass;
    }

    @Bean
    public Student student() {
        Student student = new Student();
        student.setNames(Arrays.asList("zhangsan", "lisi", "wangwu"));
        return student;
    }

}
