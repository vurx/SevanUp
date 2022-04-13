package com.vvvv.sevanUp.study.concurrency.example._3_synchronized;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedExample2 {
    private final static Logger log = LoggerFactory.getLogger(SynchronizedExample2.class);

    // 1.修饰一个类
    public void test1(int j) {
        synchronized (SynchronizedExample1.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    // 2.修饰一个静态方法
    public synchronized static void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService es = Executors.newCachedThreadPool();
        // test1,test2作用对象为整个类的对象
        // 调用对象不是同一个时，线程安全，同步执行
        es.execute(() -> {
            example1.test1(1);
        });
        es.execute(() -> {
            example2.test1(2);
        });
        es.shutdown();
    }

    // question
    // 当 example1 test1 example2 test2时 线程不安全

}
