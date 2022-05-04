package com.zhou.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.ReadwriteSplittingRuleConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.rule.ReadwriteSplittingDataSourceRuleConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author zhoubing
 * @date 2022-05-04 10:29
 */
@Configuration
public class CusConfiguration {

    @Bean
    public DataSource getDataSource() throws SQLException {
        ReadwriteSplittingDataSourceRuleConfiguration dataSourceConfig = new ReadwriteSplittingDataSourceRuleConfiguration("demo_read_query_ds", "Static", getProperties(), "demo_weight_lb");
        Properties algorithmProperties = new Properties();
        algorithmProperties.put("demo_read_ds_0", "1");
        ShardingSphereAlgorithmConfiguration algorithmConfiguration = new ShardingSphereAlgorithmConfiguration("WEIGHT", algorithmProperties);
        Map<String, ShardingSphereAlgorithmConfiguration> sphereAlgorithmConfigurationMap = new HashMap<>(1);
        sphereAlgorithmConfigurationMap.put("demo_weight_lb", algorithmConfiguration);
        ReadwriteSplittingRuleConfiguration ruleConfig = new ReadwriteSplittingRuleConfiguration(Collections.singleton(dataSourceConfig), sphereAlgorithmConfigurationMap);
        Properties properties = new Properties();
        properties.setProperty("sql-show", String.valueOf(true));
        return ShardingSphereDataSourceFactory.createDataSource(createDataSourceMap(), Collections.singleton(ruleConfig), properties);
    }

    private Properties getProperties() {
        Properties result = new Properties();
        result.setProperty("write-data-source-name", "demo_write_ds");
        result.setProperty("read-data-source-names", "demo_read_ds_0");
        return result;
    }

    public Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第 1 个数据源
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setJdbcUrl("jdbc:mysql://192.168.8.132:3306/test");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        dataSourceMap.put("demo_write_ds", dataSource1);

        // 配置第 2 个数据源
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource2.setJdbcUrl("jdbc:mysql://192.168.8.132:3305/test");
        dataSource2.setUsername("root");
        dataSource2.setPassword("123456");
        dataSourceMap.put("demo_read_ds_0", dataSource2);

        return dataSourceMap;
    }


}
