package com.zhou.oom;

/**
 * @author zhoubing
 * @date 2021-08-30 23:32
 * 如果多线程 5000个  每个1m  则需要5G
 */
public class StackOverFlow {
    public void king(){
        king();
    }

    public static void main(String[] args) {
        StackOverFlow flow = new StackOverFlow();
        flow.king();
    }
}
