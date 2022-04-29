package com.zhou.primary_key;

import com.zaxxer.hikari.HikariDataSource;
import com.zhou.jdbc.CustomDataSourceConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author zhoubing
 * @date 2022-04-29 23:39
 */
public class ForLock {
    public static Ranger lockForUnique(int gap) throws SQLException {
        final HikariDataSource dataSource = CustomDataSourceConfiguration.getDataSource();

        final Connection connection;
        connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        final Statement statement = connection.createStatement();

        ResultSet idSet = statement.executeQuery("select id from unique_lock for update;");

        if (!idSet.next()) {
            throw new SQLException("illegal statement");
        }
        int currentNum = idSet.getInt(1);

        String sql = String.format("update unique_lock set  id=id+%d where id=%d;", gap, currentNum);
        statement.execute(sql);
        connection.commit();
        statement.close();
        connection.close();
        return new Ranger(currentNum, currentNum + gap - 1);

    }
}
