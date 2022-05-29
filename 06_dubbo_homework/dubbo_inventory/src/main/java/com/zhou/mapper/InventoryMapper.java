package com.zhou.mapper;

import com.zhou.model.InventoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author zhoubing
 * @date 2022-05-28 22:49
 */
@Mapper
public interface InventoryMapper {
    @Select("select * from inventory where id=#{id}")
    InventoryDto findById(@Param("id") int id);

    @Update("update inventory set count=count-#{count}, update_time=#{updateTime} where id=#{id}")
    int update(InventoryDto inventoryDto);
}
