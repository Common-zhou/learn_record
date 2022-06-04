package com.zhou.api.common;

import com.zhou.api.filter.Filter;
import com.zhou.api.router.Router;
import lombok.Data;

import java.util.List;

/**
 * @author zhoubing
 * @date 2022-05-31 23:27
 */
@Data
public class ProxyContext {
    private Filter filter;
    private Router router;

    private List<String> urls;
}
