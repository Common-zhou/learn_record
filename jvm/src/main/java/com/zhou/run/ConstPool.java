package com.zhou.run;

/**
 * @author zhoubing
 * @date 2021-09-01 22:27
 */
public class ConstPool {
    public static void main(String[] args) {
        String b = "享学";
        String a = b + "课堂";
        // 为何两者相等？
        // 因为a.intern()是从常量池中拿。
        // a其实也会进行优化  会将其放入常量池中
        System.out.println(a.intern() == a);

        String test1 = "java";
        String test = "ja" + "va";
        String s = new StringBuilder("ja").append("va").toString();
        System.out.println(s.intern() == "java");
        System.out.println(test.intern() == test);
    }
}
