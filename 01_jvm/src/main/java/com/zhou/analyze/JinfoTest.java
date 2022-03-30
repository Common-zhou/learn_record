package com.zhou.analyze;

import java.util.concurrent.TimeUnit;

/**
 * vm args: -Xms20m -Xmx20m -Xmn10m -XX:+PrintGC
 *
 * @author zhoubing
 * @date 2021-09-01 08:36
 */
public class JinfoTest {
    public static void main(String[] args) {
        while (true) {
            byte[] b = null;
            for (int i = 0; i < 10; i++) {
                b = new byte[1 * 1024 * 1024];
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
