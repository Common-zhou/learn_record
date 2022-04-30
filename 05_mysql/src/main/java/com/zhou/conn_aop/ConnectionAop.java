package com.zhou.conn_aop;

import com.zaxxer.hikari.HikariDataSource;
import com.zhou.ThreadLocalHolder;
import com.zhou.aop.ReadOnly;
import com.zhou.datasource.RoutineDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zhoubing
 * @date 2022-04-30 13:48
 */
@Component
@Aspect
public class ConnectionAop {

    @Resource(name = "masterDataSource")
    private HikariDataSource masterDataSource;


    @Resource(name = "slaveDataSource")
    private HikariDataSource slaveDataSource;

    @Resource
    private RoutineDataSource routineDataSource;


    @Pointcut(value = "@annotation(com.zhou.aop.ReadOnly)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        injectConnection(joinPoint);

        Object proceed;
        try {
            proceed = joinPoint.proceed();
        } finally {
            Connection connection = ThreadLocalHolder.get();
            if (connection != null) {
                connection.close();
            }
        }

        return proceed;
    }

    private void injectConnection(ProceedingJoinPoint joinPoint) throws SQLException {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        ReadOnly annotation = method.getAnnotation(ReadOnly.class);

        boolean readOnly = annotation.value();

        DataSource dataSource = routineDataSource.getDataSource(!readOnly);

        Connection connection1 = dataSource.getConnection();
        ThreadLocalHolder.set(connection1);

        // 这是写死的方法....
//        if (value) {
//            Connection connection = slaveDataSource.getConnection();
//            ThreadLocalHolder.set(connection);
//        } else {
//            Connection connection = masterDataSource.getConnection();
//            ThreadLocalHolder.set(connection);
//        }
    }

}
