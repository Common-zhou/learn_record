package com.zhou.datasource;

import javax.sql.DataSource;

/**
 * @author zhoubing
 * @date 2022-04-30 14:55
 */
public interface RoutineDataSource {
    DataSource getDataSource(boolean needMaster);
}
