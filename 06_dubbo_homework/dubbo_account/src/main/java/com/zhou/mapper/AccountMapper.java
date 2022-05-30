package com.zhou.mapper;

import com.zhou.model.AccountDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Update("update account set balance=balance-#{balance},freeze_amount=freeze_amount + #{balance}, update_time = #{updateTime} where id=#{id}")
    int update(AccountDto dto);

    @Update("update account set freeze_amount=freeze_amount-#{balance}, update_time=#{updateTime} where id=#{id}")
    int confirm(AccountDto dto);

    @Update("update account set balance=balance+#{balance},freeze_amount=freeze_amount - #{balance}, update_time=#{updateTime} where id=#{id}")
    int cancel(AccountDto dto);
}
