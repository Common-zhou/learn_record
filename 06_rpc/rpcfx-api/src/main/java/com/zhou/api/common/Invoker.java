package com.zhou.api.common;

/**
 * @author zhoubing
 * @date 2022-05-19 08:53
 */
public interface Invoker {
    RpcfxResponse invoke(RpcfxRequest request);
}
