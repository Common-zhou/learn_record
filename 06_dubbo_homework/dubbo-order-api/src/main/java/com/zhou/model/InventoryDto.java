package com.zhou.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoubing
 * @date 2022-05-28 22:37
 */
@Data
public class InventoryDto implements Serializable {
    private int id;
    private String name;
    private String email;
    private int count;
    private Date createTime;
    private Date updateTime;

}

