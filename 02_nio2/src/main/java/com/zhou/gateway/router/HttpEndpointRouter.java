package com.zhou.gateway.router;

import java.util.List;

/**
 * @author zhoubing
 * @date 2022-03-30 21:45
 */
public interface HttpEndpointRouter {
    String route(List<String> endpoints);
}
