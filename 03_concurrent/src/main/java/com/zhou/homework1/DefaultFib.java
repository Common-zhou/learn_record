package com.zhou.homework1;

/**
 * @author zhoubing
 * @date 2022-04-04 16:07
 */
public class DefaultFib implements CalFib {
    int res = 0;

    @Override
    public void sum(int num) {
        this.res = fibo(num);
    }

    @Override
    public int getValue() {
        return res;
    }
}
