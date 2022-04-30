package com.zhou.primary_key;

import java.sql.SQLException;

/**
 * @author zhoubing
 * @date 2022-04-29 23:52
 */
public class MysqlFetchGapData implements FetchGapData {

    @Override
    public Ranger fetchGap(int gap) {
        Ranger ranger = null;
        try {
            ranger = ForLock.getUniqueNonOverLappingRanger(gap);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("can't get gap data");
        }
        return ranger;
    }
}
