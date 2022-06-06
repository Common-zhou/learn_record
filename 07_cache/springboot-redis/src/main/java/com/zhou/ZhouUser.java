package com.zhou;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoubing
 * @date 2022-06-06 23:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ZhouUser implements Serializable {
    private int id;
    private String username;
    private String password;
    private String salt;
    private Date createTime;
}
