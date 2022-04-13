package com.vvvv.sevanUp.study.concurrency.example._9_aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName CountDownLatchExample1
 * @Description 计数器演示
 * @Author vvvv
 * @Date 2020/8/24 17:18
 * @Version V1.0
 */
@Slf4j
public class SemaphoreExample1 {
    private final static int threadCount = 200;
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore se = new Semaphore(3);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    se.acquire();
                    test(threadNum);
                    se.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        Thread.sleep(3000);
        log.info("{}", threadNum);
    }
}