package com.zhou.load;

/**
 * @author zhoubing
 * @date 2021-08-28 23:20
 */
public class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }

    public static final String HELLOWORLD = "hello world";
}
