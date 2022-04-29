package com.zhou.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import com.zhou.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.zhou.jdbc.CustomDataSourceConfiguration.getDataSource;

/**
 * @author zhoubing
 * @date 2022-04-29 00:38
 */
public class CustomJdbc {

    public static void main(String[] args) throws SQLException {
        final HikariDataSource dataSource = getDataSource();

        final Connection connection = dataSource.getConnection();
        final List<List<Object>> res = JdbcUtil.executeQuery("select count(1) from user;", connection);

        for (List<Object> re : res) {
            System.out.println(re);
        }

    }
}
