package com.zhou.service.impl;

import com.zhou.DistributeLock;
import com.zhou.exception.SecKillException;
import com.zhou.service.MallSecKillService;
import com.zhou.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2022-06-12 15:44
 */
@Service
@Slf4j
public class MallSecKillServiceImpl implements MallSecKillService {

    @Autowired
    private DistributeLock distributeLock;

    @Autowired
    private StorageService storageService;

    private static final String LOCK_KEY_PREFIX = "lock";

    private static final int DEFAULT_WAIT_MS = 100;
    private static final int DEFAULT_EXPIRE_SEC = 5;

    @Override
    public int seckill(int productId, int number) {

        String lockKey = LOCK_KEY_PREFIX + productId;

        try {
            ensureAdequateStorage(productId, number);

            if (loopGetLockTenTimes(lockKey, 10)) {

                ensureAdequateStorage(productId, number);

                // 循环拿锁10次 如果拿到了 进行循环
                TimeUnit.MILLISECONDS.sleep(20);

                storageService.increaseStorage(productId, number * -1);

                System.out.println(String.format("%s purchase product number=[%s]", Thread.currentThread().getName(), number));

                distributeLock.releaseLock(lockKey);

                // 扣库存成功
                return 1;
            }
        } catch (Exception e) {
            throw new RuntimeException("runtime exception, please try after 10 seconds.");
        }

        throw new SecKillException("seckill fail.please try after 10 seconds.", 111);
    }

    private void ensureAdequateStorage(int productId, int number) {
        int storage = storageService.getStorage(productId);
        if (storage < number) {
            // 库存不够
            throw new SecKillException("库存不足", 110);
        }
    }

    /**
     * 循环拿锁。循环10次
     *
     * @param waitMs
     * @return
     */
    private boolean loopGetLockTenTimes(String lockKey, int waitMs) throws InterruptedException {
        if (waitMs < 0) {
            waitMs = DEFAULT_WAIT_MS;
        }
        for (int i = 0; i < 10; i++) {
            boolean lock = distributeLock.tryLock(lockKey, DEFAULT_EXPIRE_SEC);
            if (lock) {
                return true;
            }
            TimeUnit.MILLISECONDS.sleep(waitMs);
        }

        return false;
    }
}
