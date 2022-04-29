package com.zhou.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author zhoubing
 * @date 2022-04-29 23:40
 */
public class CustomDataSourceConfiguration {

    private CustomDataSourceConfiguration() {
    }

    private static volatile HikariDataSource dataSource;

//    public static HikariDataSource getDataSource() {
//        if (dataSource == null) {
//            synchronized (CommonDataSource.class) {
//                if (dataSource == null) {
//                    HikariConfig config = new HikariConfig();
//                    config.setDriverClassName("com.mysql.jdbc.Driver");
//                    config.setJdbcUrl("jdbc:mysql://192.168.8.132:3306/test");
//                    config.addDataSourceProperty("port", 3306);
//                    config.addDataSourceProperty("user", "root");
//                    config.addDataSourceProperty("password", "123456");
//
//                    dataSource = new HikariDataSource(config);
//                }
//            }
//        }
//
//
//        return dataSource;
//    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }

    static {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://192.168.8.132:3306/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false");
        config.addDataSourceProperty("port", 3306);
        config.addDataSourceProperty("user", "root");
        config.addDataSourceProperty("password", "123456");

        dataSource = new HikariDataSource(config);
    }
}
