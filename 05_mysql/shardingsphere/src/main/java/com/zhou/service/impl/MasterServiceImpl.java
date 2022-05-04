package com.zhou.service.impl;

import com.zhou.service.MasterService;
import com.zhou.util.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoubing
 * @date 2022-04-30 13:25
 */
@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private DataSource dataSource;

    /**
     * <pre>
     *      使用这个注解代表使用的是主库
     *          @ReadOnly(value = false)
     *      使用这个注解代表使用的是从库
     *          @ReadOnly / @ReadOnly(value = true)
     * </pre>
     *
     * @return
     */
    @Override
    public String query() {

        List<List<Object>> lists = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select count(1) from user");

            lists = JdbcUtil.parseResultSet(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lists.toString();
    }

    @Override
    public String insert(String name) {

        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement()) {
            String sql = String.format("insert into user(name) values (%s)", name);
            System.out.println(sql);
            boolean execute = statement.execute(sql);

            return "execute success";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return "execute failed";
    }
}
