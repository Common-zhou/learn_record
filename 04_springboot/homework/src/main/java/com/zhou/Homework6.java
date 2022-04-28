package com.zhou;

import com.zhou.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
 *
 * @author zhoubing
 * @date 2022-04-18 22:08
 */
public class Homework6 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
        ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://192.168.8.132:3306/test";

        Connection connect = null;
        try {
            connect = JdbcUtil.getConnection("com.mysql.jdbc.Driver", url, "root", "123456");

            Statement statement = connect.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from zhouzhou");

            List<List<Object>> lists = JdbcUtil.parseResultSet(resultSet);
            lists.stream().forEach(System.out::println);

            insertData(connect);
            deleteData(connect);

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connect != null) {
                connect.close();
            }
        }

    }

    public static void insertData(Connection connection) throws SQLException {
        JdbcUtil.execute("insert into zhouzhou values(3,'zhouzhou', 18)", connection);
    }

    public static void deleteData(Connection connection) throws SQLException {
        JdbcUtil.execute("delete from zhouzhou where id = 3", connection);
        JdbcUtil.execute("delete from zhouzhou where id = 4", connection);
    }
}
