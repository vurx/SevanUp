package com.vvvv.sevanUp.study.concurrency.example._2_atomic;

import com.vvvv.sevanUp.study.concurrency.annoations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassName AtomicExample4
 * @Description atomicBoolean使用类
 * @Author vvvv
 * @Date 2020/5/20 10:26
 * @Version V1.0
 */
@ThreadSafe
public class AtomicExample4 {
    private static final Logger log = LoggerFactory.getLogger(AtomicExample4.class);

    private static AtomicBoolean isHappen = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(AtomicExample1.clientTotal);
        Semaphore semaphore = new Semaphore(AtomicExample1.threadTotal);
        for (int i = 0; i < AtomicExample1.clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    public static void test(){
        if (isHappen.compareAndSet(false,true)) {
            log.info("run once time:{}",isHappen.get());
        } else {
            log.info("run error time:{}",isHappen.get());
        }
    }
}
