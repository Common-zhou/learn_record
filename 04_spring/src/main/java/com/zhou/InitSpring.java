package com.zhou;

import com.zhou.aop.ISchool;
import com.zhou.aop.Klass;
import com.zhou.aop.Student;
import com.zhou.bean.Animal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * aop时。有接口是使用$Proxy 继承原有接口
 *        无接口时，是使用EnhancerBySpringCGLIB类。直接继承原有类
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

        System.out.println("annotation aop.........");

        Klass klass = context.getBean(Klass.class);
        String res = klass.ding("zhouzhou");
        System.out.println(res);

        System.out.println(klass.getClass());

        System.out.println("annotation aop interface.........");


        // 为啥 直接拿类去拿拿不到 ==> 因为它是按照接口存的。实现类找不到。
        ISchool school = context.getBean(ISchool.class);
        school.study();

        Object iSchoolImpl = context.getBean("ISchoolImpl");
        ((ISchool) iSchoolImpl).study();

        System.out.println(school.getClass());

          for (String beanDefinitionName : context.getBeanDefinitionNames()) {

              Object bean = context.getBean(beanDefinitionName);
              System.out.println(beanDefinitionName+ "  ===>  " +bean.getClass());
          }
    }
}
