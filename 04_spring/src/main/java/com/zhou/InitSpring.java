package com.zhou;

import com.zhou.aop.Student;
import com.zhou.bean.Animal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhoubing
 * @date 2022-04-11 23:07
 */
public class InitSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContent.xml");

        Student student = context.getBean(Student.class);
        student.ding();

        System.out.println("===========================");

        System.out.println("=================method1=================");

        // method1
        Animal animal1 = (Animal) context.getBean("animal1");
        System.out.println(animal1);

        System.out.println("=================method2=================");
        Animal animal2 = (Animal) context.getBean("animal2");
        System.out.println(animal2);


        System.out.println("=================method3=================");
        Animal animal3 = (Animal) context.getBean("animal3");
        System.out.println(animal3);

        //System.out.println("=================method4=================");
        //Animal animal4 = (Animal) context.getBean("animal4");
        //System.out.println(animal4);

    }
}
