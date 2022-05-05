package com.zhou.sharding;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 这个是用来批量插入sharding的代码。
 * 使用sharding-proxy来完成分片。
 * 分片见配置 sharding/config-sharding.yaml
 * 创表逻辑见：sharding-init.sql
 *
 * @author zhoubing
 * @date 2022-05-06 00:35
 */
public class initData {

    public static HikariDataSource getShardingDataSource() {
        // 配置第 1 个数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://192.168.8.132:13307/sharding_db");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    public static void main(String[] args) throws SQLException {
        HikariDataSource shardingDataSource = getShardingDataSource();

        Connection connection = shardingDataSource.getConnection();

        insertData(connection);

    }

    public static void insertData(Connection connection) {

        try (Statement statement = connection.createStatement()) {

            for (int i = 0; i < 1000; i++) {
                String sql = String.format("insert into t_order(order_id, user_id, order_name) values (%d,%d,concat('hello world', %d));", i, i, i);
                System.out.println(sql);
                boolean execute = statement.execute(sql);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
