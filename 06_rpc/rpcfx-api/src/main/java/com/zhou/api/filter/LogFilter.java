package com.zhou.api.filter;

import com.alibaba.fastjson.JSON;
import com.zhou.api.common.RpcfxRequest;

/**
 * @author zhoubing
 * @date 2022-05-31 23:41
 */
public class LogFilter implements Filter {
    @Override
    public boolean filter(RpcfxRequest request) {
        System.out.println("[filter got request]" + JSON.toJSONString(request));
        return true;
    }
}
