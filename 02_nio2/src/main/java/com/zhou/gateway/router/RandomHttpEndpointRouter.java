package com.zhou.gateway.router;

import java.util.List;
import java.util.Random;

/**
 * @author zhoubing
 * @date 2022-03-30 21:46
 */
public class RandomHttpEndpointRouter implements HttpEndpointRouter{

    Random random = new Random();

    @Override
    public String route(List<String> endpoints) {
        if (endpoints == null || endpoints.size() == 0){
            return "";
        }
        int index = random.nextInt(endpoints.size());

        return endpoints.get(index);
    }
}
