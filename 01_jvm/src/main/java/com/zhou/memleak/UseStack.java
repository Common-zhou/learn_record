package com.zhou.memleak;

/**
 * @author zhoubing
 * @date 2021-08-30 23:40
 */
public class UseStack {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(new Object());

        System.out.println(stack.pop());

        System.out.println(stack.elements[0]);

    }
}
