package com.zhou.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoubing
 * @date 2022-04-04 14:13
 */
public class LockCounter {
    private int num;
    private Lock lock = new ReentrantLock();

    public int addAndGet() {
        try {
            lock.lock();
            return ++num;
        } finally {
            lock.unlock();
        }
    }

    public int get() {
        return num;
    }
}
