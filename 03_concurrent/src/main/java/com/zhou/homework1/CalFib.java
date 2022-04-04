package com.zhou.homework1;

/**
 * @author zhoubing
 * @date 2022-04-04 15:26
 */
public interface CalFib {
    void sum(int num);

    int getValue();

    default int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}
