package com.zhou.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoubing
 * @date 2022-04-03 23:16
 */
public class ConditionDemo {
    private Lock lock = new ReentrantLock();
    private Condition nonFull = lock.newCondition();
    private Condition nonEmpty = lock.newCondition();

    private List<String> list = new ArrayList<>();
    private int size = 20;

    public boolean put(String value) throws InterruptedException {
        lock.lock();
        while (list.size() >= size) {
            // 满了 等着
            System.out.println(String.format("%s full,await.....", Thread.currentThread().getName()));
            nonFull.await();
        }
        try {
            boolean add = list.add(value);
            return add;
        } finally {
            // 添加了一个 非空的single一下
            nonEmpty.signal();
            lock.unlock();
        }

    }

    public String getLast() throws InterruptedException {
        lock.lock();
        while (list.size() == 0) {
            System.out.println(String.format("%s empty,await.....", Thread.currentThread().getName()));
            nonEmpty.await();
        }
        try {
            String item = list.remove(list.size() - 1);
            nonFull.signal();
            return item;
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {

        ConditionDemo conditionDemo = new ConditionDemo();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    conditionDemo.put(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    String last = conditionDemo.getLast();
                    System.out.println("get data: " + last);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

}
