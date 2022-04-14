package com.vvvv.sevanUp.study.concurrency.example._1_count;


import com.vvvv.sevanUp.study.concurrency.annoations.ThreadNotSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName CountExample1
 * @Description 多线程测试
 * @Author vvvv
 * @Date 2020/5/20 09:36
 * @Version V1.0
 */
@ThreadNotSafe
public class CountExample1 {
    private static final Logger log = LoggerFactory.getLogger(CountExample1.class);
    //客户端请求的总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 20;

    private static int count = 0;

    private static void add() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        // 线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        // 信号量，每次允许同时执行的线程数量
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 计数器闭锁 当countDownLatch为0时 主线程才可继续
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executor.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        // 上述的线程全部执行完
        countDownLatch.await();
        executor.shutdown();
        log.info("count:{}",count);
    }

}
