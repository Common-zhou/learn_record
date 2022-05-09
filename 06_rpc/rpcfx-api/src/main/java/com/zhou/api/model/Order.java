package com.zhou.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhoubing
 * @date 2022-05-09 22:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int id;
    private String name;
    private Date createTime;
}
