package com.zhou.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author zhoubing
 * @date 2022-04-30 14:58
 */
@Component
public class RobinRoutingDataSource implements RoutineDataSource, ApplicationRunner {

    @Autowired
    private ApplicationContext context;

    private DataSource masterDataSource = null;
    private List<DataSource> slaveDataSources = new ArrayList<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        List<String> slaveBeanDefinitionNames = new ArrayList<>();
        String masterBeanDefinitionName = "";

        Pattern slavePattern = Pattern.compile("slaveDataSource(\\w)*");
        Pattern masterPattern = Pattern.compile("masterDataSource(\\w)*");

        System.out.println("=============================");
        for (String beanDefinitionName : beanDefinitionNames) {
            if (slavePattern.matcher(beanDefinitionName).matches()) {
                // 是slave的bean
                System.out.println("found slave name: " + beanDefinitionName);
                slaveBeanDefinitionNames.add(beanDefinitionName);
                slaveDataSources.add((DataSource) context.getBean(beanDefinitionName));

            } else if (masterPattern.matcher(beanDefinitionName).matches()) {
                // 是master的bean
                System.out.println("found master name: " + beanDefinitionName);

                masterDataSource = (DataSource) context.getBean(beanDefinitionName);
            }
        }
        System.out.println("=============================");

    }


    @Override
    public DataSource getDataSource(boolean needMaster) {

        if (needMaster) {
            // 需要master的
            return masterDataSource;
        }

        Random random = new Random();
        return slaveDataSources.get(random.nextInt(slaveDataSources.size()));

    }
}
