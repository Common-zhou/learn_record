package com.zhou;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import com.mysql.jdbc.MySQLConnection;
import com.zhou.model.MUserTag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/05/12 12:33
 */
public class ResultRowTest {
  public static Connection getConnection(String driverClass, String url, String userName, String password)
      throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    Class<?> aClass = Class.forName(driverClass);
    Driver driver = (Driver) aClass.newInstance();

    Properties properties = new Properties();
    properties.setProperty("user", userName);
    properties.setProperty("password", password);

    return driver.connect(url, properties);
  }

  //public static HikariDataSource getDataSource() {
  //  HikariConfig config = new HikariConfig();
  //  config.setDriverClassName("com.mysql.jdbc.Driver");
  //  config.setJdbcUrl("jdbc:mysql://10.120.101.147:3305/metadata");
  //  config.addDataSourceProperty("port", 3305);
  //  config.addDataSourceProperty("user", "work");
  //  config.addDataSourceProperty("password", "00@yiMapV#");
  //
  //  return new HikariDataSource(config);
  //}

  public static DataSource getDataSource(){
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setJdbcUrl("jdbc:mysql://10.120.101.147:3305/metadata");
    dataSource.setPassword("00@yiMapV#");
    dataSource.setUser("work");
    dataSource.setDriverClass("com.mysql.jdbc.Driver");

    return dataSource;
  }

  public static void main(String[] args)
      throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    String url = "jdbc:mysql://10.120.101.147:3305/metadata";

    MySQLConnection connect = (MySQLConnection)getConnection("com.mysql.jdbc.Driver", url, "work", "00@yiMapV#");

    boolean useGmtMillis = connect.getUseGmtMillisForDatetimes();
    System.out.println(useGmtMillis);

    //Statement statement = connect.createStatement();

    //ResultSet resultSet = statement.executeQuery("select id,update_time from sp_user_tag where id=7");
    //
    //
    //while (resultSet.next()) {
    //  Timestamp timestamp = resultSet.getTimestamp(2);
    //
    //  Object object = resultSet.getObject(2);
    //  System.out.println(object);
    //
    //  System.out.println(timestamp);
    //
    //  Object resultSetValue = JdbcUtils.getResultSetValue(resultSet, 2, Timestamp.class);
    //  System.out.println(resultSetValue);
    //
    //}

    NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

    jdbcTemplate.query("select id,update_time from sp_user_tag where id=7", new RowMapper<MUserTag>() {
      @Override
      public MUserTag mapRow(ResultSet rs, int rowNum) throws SQLException {

        String string = rs.getString(1);
        Timestamp timestamp = rs.getTimestamp(2);
        System.out.println(string + "===" + timestamp);

        return null;
      }
    });



  }
}
