package com.zhou.primary_key;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhoubing
 * @date 2022-04-29 23:54
 */
public class IdGenerator {

    private static final int GAP = 2000;
    private static final int THREAD_SIZE = 100;

    private static AtomicLong generateNum = new AtomicLong(0);
    private static Map<Integer, Object> map = new ConcurrentHashMap<>(10_000_000);

    private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_SIZE);

    private static Object objectVal = new Object();

    static class IdGeneratorRunnable implements Runnable {
        FetchGapData fetchGapData = new MysqlFetchGapData();

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                Ranger ranger = fetchGapData.fetchGap(GAP);
                for (int j = ranger.getMin(); j <= ranger.getMax(); j++) {
                    generateNum.incrementAndGet();
                    map.put(j, objectVal);
                }
            }

            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < THREAD_SIZE; i++) {
            new Thread(new IdGeneratorRunnable()).start();
        }

        countDownLatch.await();

        long generatedNum = generateNum.get();
        int notRepeatSize = map.size();
        if (generatedNum == notRepeatSize) {
            System.out.println(String.format("generate down.not repeat, size = %s", notRepeatSize));
        } else {
            throw new RuntimeException(
                    String.format("has repeat value.[generatedNum = %d, notRepeatSize =%d]", generatedNum, notRepeatSize));
        }

    }
}
