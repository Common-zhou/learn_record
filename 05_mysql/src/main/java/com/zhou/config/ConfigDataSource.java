package com.zhou.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoubing
 * @date 2022-04-29 00:50
 */
@Configuration
public class ConfigDataSource {
    @Bean
    public HikariDataSource masterDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://192.168.8.132:3306/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false");
        config.addDataSourceProperty("port", 3306);
        config.addDataSourceProperty("user", "root");
        config.addDataSourceProperty("password", "123456");

        return new HikariDataSource(config);
    }

    @Bean
    public HikariDataSource slaveDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://192.168.8.132:3305/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false");
        config.addDataSourceProperty("port", 3306);
        config.addDataSourceProperty("user", "root");
        config.addDataSourceProperty("password", "123456");

        return new HikariDataSource(config);
    }

}
