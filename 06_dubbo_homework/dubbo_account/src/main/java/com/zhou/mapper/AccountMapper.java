package com.zhou.mapper;

import com.zhou.model.AccountDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author zhoubing
 * @date 2022-05-28 22:25
 */
@Mapper
@Repository
public interface AccountMapper {
    @Select("select * from account where id=#{id}")
    AccountDto findById(@Param("id") int id);
}
