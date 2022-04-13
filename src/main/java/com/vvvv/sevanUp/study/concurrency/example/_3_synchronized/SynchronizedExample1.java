package com.vvvv.sevanUp.study.concurrency.example._3_synchronized;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedExample1 {
    private final static Logger log = LoggerFactory.getLogger(SynchronizedExample1.class);

    // 1.修饰一个代码块
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    // 2.修饰一个方法
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService es = Executors.newCachedThreadPool();
        // test1,test2作用对象为调用对象
        // 调用对象为同一个时，线程安全，同步执行
//        es.execute(() -> {
//            example1.test2(1);
//        });
//        es.execute(() -> {
//            example1.test2(2);
//        });
        // 调用对象不是同一个时，线程不安全，异步执行
        es.execute(() -> {
            example1.test1(1);
        });
        es.execute(() -> {
            example2.test1(2);
        });
        es.shutdown();
    }
}
