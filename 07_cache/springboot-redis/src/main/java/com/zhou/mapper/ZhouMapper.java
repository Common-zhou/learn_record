package com.zhou.mapper;

import com.zhou.ZhouUser;
import org.apache.ibatis.annotations.*;

/**
 * @author zhoubing
 * @date 2022-05-28 22:49
 */
@Mapper
public interface ZhouMapper {
    @Select("select * from zhou where id=#{id}")
    ZhouUser findById(@Param("id") int id);

    @Insert("insert into zhou(username,password,salt) values (#{username},#{password},#{salt})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("zhou") ZhouUser zhouUser);

}
