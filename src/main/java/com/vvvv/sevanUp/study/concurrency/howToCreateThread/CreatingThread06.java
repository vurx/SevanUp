package com.vvvv.sevanUp.study.concurrency.howToCreateThread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CreatingThread06
 * @Description 线程池
 * @Author vvvv
 * @Date 2020/7/22 14:03
 * @Version V1.0
 */
public class CreatingThread06 {
    @Test
    public void test(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName());
            });
        }
        executorService.shutdown();
    }
}