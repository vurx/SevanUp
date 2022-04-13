package com.vvvv.sevanUp.study.concurrency.howToSolveExc;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName UseTryCatch
 * @Description 解决异常
 * @Author vvvv
 * @Date 2020/7/22 14:58
 * @Version V1.0
 */
public class SolveExc {
    @Test
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.submit(()->{
                System.out.println(Thread.currentThread().getName());
                Object a = null;
                try {
                    System.out.println(a.toString());
                } catch (Exception e) {
                    System.out.println("异常啦");
                }
            });
        }
    }

    @Test
    public void test2() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            Future<?> future = executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                Object a = null;
                System.out.println(a.toString());
            });
            try {
                future.get();
            } catch (Exception e) {
                System.out.println("出错啦");
            }
        }
    }
}