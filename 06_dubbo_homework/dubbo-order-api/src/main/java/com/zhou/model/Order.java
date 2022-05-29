package com.zhou.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhoubing
 * @date 2022-05-29 20:20
 */
@Data
@Builder
@Accessors(chain = true)
public class Order implements Serializable {
    private int id;
    private int accountId;
    private int inventoryId;
    private BigDecimal totalAmount;
    private int count;
    private String status;
    private Date createTime;
    private Date updateTime;

    public enum OrderStatus {
        SUCCESS, FAILURE, UNPAID, CANCEL
    }
}
