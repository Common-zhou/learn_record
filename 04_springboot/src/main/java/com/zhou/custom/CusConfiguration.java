package com.zhou.custom;

import com.zhou.school.Klass;
import com.zhou.school.Student;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoubing
 * @date 2022-04-17 22:25
 */
@Configuration
public class CusConfiguration {
    @Bean
    public Klass klass() {
        Klass klass = new Klass();
        klass.setStudents(Arrays.asList(new Student(1, "zhoubing"), new Student(4, "test_helloworld")));
        return klass;
    }
}
