package com.zhou.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zhou.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zhoubing
 * @date 2022-04-29 00:38
 */
public class CustomJdbc {

    public static HikariDataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://192.168.8.132:3305/test");
        config.addDataSourceProperty("port", 3306);
        config.addDataSourceProperty("user", "root");
        config.addDataSourceProperty("password", "123456");

        return new HikariDataSource(config);
    }



    public static void main(String[] args) throws SQLException {
        final HikariDataSource dataSource = getDataSource();

        final Connection connection = dataSource.getConnection();
        final List<List<Object>> res = JdbcUtil.executeQuery("select count(1) from user;", connection);

        for (List<Object> re : res) {
            System.out.println(re);
        }

    }
}
