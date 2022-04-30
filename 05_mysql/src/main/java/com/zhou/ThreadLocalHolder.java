package com.zhou;

import java.sql.Connection;

public class ThreadLocalHolder {

    private final static ThreadLocal<Connection> ThreadLocalHolder = new ThreadLocal<>();

    /**
     * 重写ThreadLocal的三个方法：set、get、remove
     */
    public static void set(Connection connection) {
        ThreadLocalHolder.set(connection);
    }

    public static Connection get() {
        return ThreadLocalHolder.get();
    }

    public static void remove() {
        ThreadLocalHolder.remove();
    }

}
