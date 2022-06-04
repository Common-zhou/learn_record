package com.zhou.api.filter;

import com.zhou.api.common.RpcfxRequest;

/**
 * @author zhoubing
 * @date 2022-05-31 23:20
 */
public interface Filter {
    boolean filter(RpcfxRequest request);
}
