package com.zhou.service.impl;

import com.zhou.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author zhoubing
 * @date 2022-06-12 15:58
 */
@Service
public class StorageServiceImpl implements StorageService {
    private static final String STORAGE_KEY_PREFIX = "storage_";

    @Autowired
    JedisPool jedisPool;

    @Override
    public int increaseStorage(int productId, int number) {
        String storageKey = STORAGE_KEY_PREFIX + productId;
        try (Jedis jedis = jedisPool.getResource()) {
            Long incr = jedis.incrBy(storageKey, number);
            return 1;
        }
    }

    @Override
    public int getStorage(int productId) {
        String storageKey = STORAGE_KEY_PREFIX + productId;
        try (Jedis jedis = jedisPool.getResource()) {
            String storage = jedis.get(storageKey);
            return Integer.parseInt(storage);
        }
    }
}
