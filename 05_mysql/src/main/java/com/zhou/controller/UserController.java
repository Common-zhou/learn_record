package com.zhou.controller;

import com.zaxxer.hikari.HikariDataSource;
import com.zhou.util.JdbcUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zhoubing
 * @date 2022-04-29 00:49
 */
@RestController
public class UserController {

    @Resource(name = "masterDataSource")
    private HikariDataSource masterDataSource;


    @Resource(name = "slaveDataSource")
    private HikariDataSource slaveDataSource;


    @RequestMapping("/test/database1")
    public String queryDatabase(String database) throws SQLException {

        Connection connection = null;

        if ("master".equals(database)) {
            connection = masterDataSource.getConnection();
        } else {
            connection = slaveDataSource.getConnection();

        }


        final List<List<Object>> res = JdbcUtil.executeQuery("select count(1) from user;", connection);

        return res.toString();
    }
}
