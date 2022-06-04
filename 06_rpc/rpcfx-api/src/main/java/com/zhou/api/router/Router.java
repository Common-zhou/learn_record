package com.zhou.api.router;

import java.util.List;

/**
 * @author zhoubing
 * @date 2022-05-31 23:23
 */
public interface Router {
    String route(List<String> urls);
}
