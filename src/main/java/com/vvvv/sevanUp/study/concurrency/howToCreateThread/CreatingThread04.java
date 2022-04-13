package com.vvvv.sevanUp.study.concurrency.howToCreateThread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName CreatingThread04
 * @Description 实现Callabe接口
 * @Author vvvv
 * @Date 2020/7/22 10:24
 * @Version V1.0
 */
public class CreatingThread04 implements Callable<Long> {
    @Override
    public Long call() throws Exception {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getId() + " is running");
        return Thread.currentThread().getId();
    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        //FutureTask实际上实现了Runnable接口
        FutureTask<Long> future = new FutureTask<>(new CreatingThread04());
        new Thread(future).start();
        System.out.println("等待任务完成");
        Long aLong = future.get();
        System.out.println("result is " + aLong);
    }
}