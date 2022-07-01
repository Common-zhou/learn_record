package com.zhou.scanner;

import static java.sql.DriverManager.getConnection;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import com.mysql.jdbc.MySQLConnection;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.sql.DataSource;

/**
 * 定期扫描订单表 改状态
 *
 * @author zhoubing
 * @since 2022/06/22 23:19
 */
public class OrderStatusScanner {

  public static DataSource getDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setJdbcUrl("jdbc:mysql://10.120.101.147:3305/metadata");
    dataSource.setPassword("00@yiMapV#");
    dataSource.setUser("work");
    dataSource.setDriverClass("com.mysql.jdbc.Driver");

    return dataSource;
  }

  public static void main(String[] args) throws SQLException {
    String url = "jdbc:mysql://10.120.101.147:3305/metadata";

    MySQLConnection connect = (MySQLConnection) getConnection(url, "work", "00@yiMapV#");

    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    // 5s 扫一次库 设置状态
    ScheduledFuture<?> scheduledFuture =
        scheduledExecutorService.scheduleAtFixedRate(new ScheduledUpdateOrderStatusRunnable(connect), 0, 5,
            TimeUnit.SECONDS);

  }


  public static class ScheduledUpdateOrderStatusRunnable implements Runnable {
    private MySQLConnection connect;

    public ScheduledUpdateOrderStatusRunnable(MySQLConnection connect) {
      this.connect = connect;
    }

    @Override
    public void run() {

      try {
        Statement statement = connect.createStatement();
        List<Integer> ids = findUnHandledOrder(statement);
        batchUpdateStatus(statement, ids);
      } catch (SQLException e) {
        e.printStackTrace();
      }

    }

    private void batchUpdateStatus(Statement statement, List<Integer> ids) throws SQLException {
      if (CollectionUtils.isEmpty(ids)) {
        System.out.println("no need to handle");
        return;
      }

      System.out.printf("prepare handle ids.[%s]\n", ids);

      String idsJoinging = ids.stream().map(String::valueOf).collect(Collectors.joining(",", "(", ")"));

      String sql = String.format("update order_queue set status=1 where id in %s", idsJoinging);

      boolean execute = statement.execute(sql);

    }

    private List<Integer> findUnHandledOrder(Statement statement) throws SQLException {
      ResultSet resultSet = statement.executeQuery("select id,price from order_queue where status=0");

      List<Integer> ids = new ArrayList<>();
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        ids.add(id);
      }

      return ids;
    }
  }

}
