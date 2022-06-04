package com.zhou.api.router;

import java.util.List;
import java.util.Random;

/**
 * @author zhoubing
 * @date 2022-06-04 13:27
 */
public class RandomRouter implements Router {
    @Override
    public String route(List<String> urls) {
        if (urls == null || urls.size() == 0) {
            return "";
        }
        int len = urls.size();

        Random random = new Random();
        int selectIndex = random.nextInt(len);
        String selectUrl = urls.get(selectIndex);

        System.out.printf("router select url = [%s]", selectUrl);
        return selectUrl;
    }
}
