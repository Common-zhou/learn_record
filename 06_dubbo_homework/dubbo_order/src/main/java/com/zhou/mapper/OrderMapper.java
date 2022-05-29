package com.zhou.mapper;

import com.zhou.model.Order;
import org.apache.ibatis.annotations.*;

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
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(Order order);

    @Update("update `order` set status=#{status} where id=#{id}")
    void updateStatus(@Param("id") int id, @Param("status") String status);
}
