package com.zhou.load;

/**
 * 被动引用
 * 通过子类引用父类的静态字段，不会导致子类初始化。
 *
 * @author zhoubing
 * @date 2021-08-28 23:12
 */
public class NotInitialization {
    public static void main(String[] args) {
        // 只会初始化父类，子类不初始化
        //System.out.println(SubClass.value);

        //子类和父类都会初始化
        //System.out.println(SubClass.val2);

        //case2();
        case3();
    }

    public static void case2(){
        // 不会初始化SuperClass
        SuperClass[] sca = new SuperClass[10];
    }

    public static void case3(){
        // 不会初始化ConstClass 因为常量在编译阶段会存入<<<调用类>>>的常量池中，本质上并没有直接引用定义常量的类，
        // 因此不会触发定义常量类的初始化
        // 会存入 NotInitialization 的常量池中
        System.out.println(ConstClass.HELLOWORLD);
    }
}
