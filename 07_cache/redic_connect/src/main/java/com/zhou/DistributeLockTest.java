package com.zhou;

import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2022-06-11 19:56
 */
public class DistributeLockTest {

    ThreadLocal<String> distributeLockValue = new ThreadLocal<>();

    JedisPool jedisPool = new JedisPool("192.168.8.132", 6379);

    public static void main(String[] args) {

        final DistributeLockTest distributeLockTest = new DistributeLockTest();
        // lua脚本执行
        //distributeLockTest.lua("zhangsan", "lisi", "100");

        ExecutorService executorService = Executors.newFixedThreadPool(200);

        for (int i = 0; i < 1000; i++) {

            String lockKey = "distribute_key" + "1";

            executorService.submit(() -> {
                boolean getLock = distributeLockTest.tryLock(lockKey, 30);
                if (getLock) {
                    // 拿到锁了
                    System.out.println(Thread.currentThread().getName() + " === got lock");

                    try {
                        // 进行业务处理
                        TimeUnit.SECONDS.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        distributeLockTest.releaseLock(lockKey);
                    }

                } else {
                    System.out.println(Thread.currentThread().getName() + " [ *** no lock]");
                }
            });
        }


        executorService.shutdown();

    }

    public boolean tryLock(String lockKey, int expireTime) {
        try (Jedis resource = jedisPool.getResource()) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String result = resource.set(lockKey, uuid, "NX", "EX", expireTime);

            if (!StringUtils.isEmpty(resource) && "OK".equalsIgnoreCase(result)) {
                distributeLockValue.set(uuid);
                // 不为空 且返回的是OK
                return true;
            }
        }

        return false;
    }

    public void releaseLock(String lockKey) {

        String currentThreadValue = distributeLockValue.get();
        if (StringUtils.isEmpty(currentThreadValue)) {
            return;
        }

        try (Jedis resource = jedisPool.getResource()) {
            String value = resource.get(lockKey);
            // 防止这种情况： A线程获取了锁，设置了超时时间30s。
            // A执行时间超过了30s。锁被释放了(因为过期)，别的线程获取到了锁
            // 当A执行完成之后，去释放了锁。但这把锁不属于A。
            if (currentThreadValue.equals(value)) {
                Long del = resource.del(lockKey);
                System.out.println(del);
            }

        }
    }

    /**
     * lua脚本。
     *
     * @param key        需要设置的key
     * @param value      设置的value
     * @param expireTime 过期时间
     */
    public void lua(String key, String value, String expireTime) {
        String luaStr = "if (redis.call('setnx',KEYS[1],ARGV[1]) < 1) then return 0; end; redis.call('expire',KEYS[1],tonumber(ARGV[2])); return 1;";

        Jedis resource = jedisPool.getResource();
        Object eval = resource.eval(luaStr, 1, key, value, expireTime);
        System.out.println(eval);

    }
}
