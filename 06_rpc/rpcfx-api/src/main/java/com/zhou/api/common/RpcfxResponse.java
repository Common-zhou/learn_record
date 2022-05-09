package com.zhou.api.common;

import lombok.Data;

/**
 * @author zhoubing
 * @date 2022-05-09 00:15
 */
@Data
public class RpcfxResponse {
    /**
     * 状态码 0为正常
     * 其余均为不正常
     */
    private int status;

    private String result;

    /**
     * 如果有异常。使用这个带回来
     */
    private String exception;
}
