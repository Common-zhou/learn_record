package com.zhou.load;

/**
 * @author zhoubing
 * @date 2021-08-28 23:13
 */
public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }

    public static int val2 = 133;
}
