package com.zhou.homework;

/**
 * 1. 总结一下，单例的各种写法，比较它们的优劣。
 *
 * @author zhoubing
 * @date 2022-04-13 23:46
 */
public class Homework1 {

    /**
     * 饿汉式 上来就加载。
     */
    static class Singleton1 {
        private static Singleton1 instance = new Singleton1();

        private Singleton1() {
        }

        public Singleton1 getInstance() {
            return instance;
        }
    }

    /**
     * 双重校验 + 锁
     */
    static class Singleton2 {
        private volatile static Singleton2 instance;

        private Singleton2() {
        }

        public static Singleton2 getInstance() {
            if (instance == null) {
                synchronized (Singleton2.class) {
                    if (instance == null) {
                        instance = new Singleton2();
                    }
                }
            }
            return instance;
        }
    }

    static class Singleton3 {

        private Singleton3() {
        }

        private static class Singleton3Inner {
            public static Singleton3 singleton3 = new Singleton3();
        }

        public static Singleton3 getInstance() {
            return Singleton3Inner.singleton3;
        }
    }
}
