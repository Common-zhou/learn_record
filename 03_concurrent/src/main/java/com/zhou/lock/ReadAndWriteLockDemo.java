package com.zhou.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhoubing
 * @date 2022-04-03 22:43
 */
public class ReadAndWriteLockDemo {
    private final Map<String, Object> map = new HashMap<>();

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public Object readValue(String key) {
        System.out.println(Thread.currentThread().getName() + " --> 拿读锁......");
        readWriteLock.readLock().lock();
        Object value = null;
        try {
            value = map.get(key);

            if (value == null) {
                System.out.println(Thread.currentThread().getName() + " --> 释放读锁,拿写锁......");
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try {
                    value = "hello world";
                } finally {
                    System.out.println(Thread.currentThread().getName() + " --> 释放写锁,拿读锁......");
                    readWriteLock.writeLock().unlock();
                    readWriteLock.readLock().lock();
                }

            }
            return value;

        } finally {
            System.out.println(Thread.currentThread().getName() + " --> 释放读锁......");
            readWriteLock.readLock().unlock();
        }

    }

    public static void main(String[] args) {
        ReadAndWriteLockDemo readAndWriteLockDemo = new ReadAndWriteLockDemo();
        Object testZhou = readAndWriteLockDemo.readValue("testZhou");
        System.out.println(testZhou);
    }

}
