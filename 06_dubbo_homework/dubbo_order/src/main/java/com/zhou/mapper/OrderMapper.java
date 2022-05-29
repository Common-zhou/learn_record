package com.zhou.mapper;

import com.zhou.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhoubing
 * @date 2022-05-29 20:16
 */
@Mapper
public interface OrderMapper {
    /**
     * 保存订单.
     *
     * @param order 订单对象
     * @return rows int
     */
    @Insert(" insert into `order` (account_id,inventory_id,total_amount,count,create_time,update_time) " +
            " values ( #{accountId},#{inventoryId},#{totalAmount},#{count},#{createTime},#{updateTime})")
    int save(Order order);
}
