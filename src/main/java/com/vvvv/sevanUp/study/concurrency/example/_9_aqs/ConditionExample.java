package com.vvvv.sevanUp.study.concurrency.example._9_aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionExample
 * @Description Condition示例
 * @Author vvvv
 * @Date 2020/9/16 17:09
 * @Version V1.0
 */
@Slf4j
public class ConditionExample {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            try {
                log.info("线程1 get lock");
                lock.lock();
                log.info("线程1 wait signal");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("线程1 get signal");
            lock.unlock();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
                lock.lock();
                log.info("线程2 get lock");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("线程2 send signal ~");
            condition.signal();
            lock.unlock();
        }).start();
    }
}