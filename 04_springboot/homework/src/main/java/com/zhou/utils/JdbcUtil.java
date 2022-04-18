package com.zhou.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author zhoubing
 * @date 2022-04-18 23:01
 */
public class JdbcUtil {
    public static Connection getConnection(String driverClass, String url, String userName, String password)
        throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> aClass = Class.forName(driverClass);
        Driver driver = (Driver) aClass.newInstance();

        Properties properties = new Properties();
        properties.setProperty("user", userName);
        properties.setProperty("password", password);

        return driver.connect(url, properties);
    }


    public static List<List<Object>> parseResultSet(ResultSet resultSet) throws SQLException {
        List<List<Object>> result = new ArrayList<>();

        if (resultSet == null) {
            return result;
        }

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            List<Object> oneLine = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                Object object = resultSet.getObject(i);
                oneLine.add(object);
            }
            result.add(oneLine);
        }

        return result;
    }

    public static void execute(String sql, Connection connection) {
        try (Statement statement = connection.createStatement()) {
            final boolean execute = statement.execute(sql);
            System.out.println(execute);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
