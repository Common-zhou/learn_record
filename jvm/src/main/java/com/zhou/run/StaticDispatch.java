package com.zhou.run;

/**
 * 静态分派，
 *
 * @author zhoubing
 * @date 2021-08-28 18:30
 */
public class StaticDispatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,man!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,gentleman!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();

        // 静态类型变化
        //sr.sayHello(man);
        //sr.sayHello(woman);

        //sr.sayHello((Man) man);
        //sr.sayHello((Woman) woman);

    }
}
