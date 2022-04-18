package com.zhou;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zhou.utils.JdbcUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author zhoubing
 * @date 2022-04-18 23:38
 */
public class Homework6_2 {
    public static HikariDataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://192.168.8.132:3306/test");
        config.addDataSourceProperty("port", 3306);
        config.addDataSourceProperty("user", "root");
        config.addDataSourceProperty("password", "123456");

        return new HikariDataSource(config);
    }

    public static void main(String[] args) {
        final HikariDataSource dataSource = getDataSource();
        try(final Connection connection = dataSource.getConnection();
            final Statement statement = connection.createStatement();) {
            final ResultSet resultSet = statement.executeQuery("select * from zhouzhou");
            final List<List<Object>> lists = JdbcUtil.parseResultSet(resultSet);
            System.out.println(lists);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
