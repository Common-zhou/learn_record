package com.zhou.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhoubing
 * @date 2022-05-11 08:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private int id;
    private String name;
}
