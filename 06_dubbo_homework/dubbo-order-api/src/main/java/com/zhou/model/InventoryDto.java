package com.zhou.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoubing
 * @date 2022-05-28 22:37
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class InventoryDto implements Serializable {
    private int id;
    private String name;
    private String email;
    private int count;
    private int freezeCount;
    private Date createTime;
    private Date updateTime;

}

