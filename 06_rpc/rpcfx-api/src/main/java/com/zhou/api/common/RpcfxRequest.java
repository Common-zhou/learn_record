package com.zhou.api.common;

import lombok.Data;

/**
 * @author zhoubing
 * @date 2022-05-09 00:13
 */
@Data
public class RpcfxRequest {
    /**
     * 请求的类。目前传全限定名
     */
    private String serviceClass;

    private String methodName;

    private Object[] params;


}
