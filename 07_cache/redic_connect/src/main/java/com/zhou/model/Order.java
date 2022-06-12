package com.zhou.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhoubing
 * @date 2022-06-12 22:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private String status;

    private Integer money;

    private Date createTime;
    private Date updateTime;
}
