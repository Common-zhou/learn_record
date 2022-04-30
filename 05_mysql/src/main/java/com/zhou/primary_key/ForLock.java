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
    public static Ranger getUniqueNonOverLappingRanger(int gap) throws SQLException {
        final HikariDataSource dataSource = CustomDataSourceConfiguration.getDataSource();

        Integer currentNum = null;

        try (final Connection connection = dataSource.getConnection();
             final Statement statement = connection.createStatement();) {
            connection.setAutoCommit(false);
            currentNum = lockAndGetBegin(statement);

            boolean updateSuccess = updateUniqueLock(statement, currentNum, gap);
            if (updateSuccess) {
                connection.commit();
            } else {
                connection.rollback();
            }
        } finally {

        }
        return new Ranger(currentNum, currentNum + gap - 1);

    }

    private static boolean updateUniqueLock(Statement statement, int currentNum, int gap) throws SQLException {
        String sql = String.format("update unique_lock set  id=id+%d where id=%d;", gap, currentNum);
        return statement.execute(sql);
    }

    private static int lockAndGetBegin(Statement statement) throws SQLException {

        ResultSet idSet = statement.executeQuery("select id from unique_lock for update;");

        if (!idSet.next()) {
            throw new SQLException("illegal statement");
        }
        return idSet.getInt(1);
    }
}
