package com.vvvv.sevanUp.study.concurrency.example._10_deadLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName DeadLock
 * @Description 死锁展示
 * @Author vvvv
 * @Date 2020/8/26 15:25
 * @Version V1.0
 */
@Slf4j
public class DeadLock implements Runnable {

    public int flag = 1;
    public static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        log.info("flag:{}", flag);
        if (flag == 1) {
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    log.info("1");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    log.info("2");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock d1 = new DeadLock();
        DeadLock d2 = new DeadLock();
        d1.flag = 1;
        d2.flag = 0;
        new Thread(d1).start();
        new Thread(d2).start();
    }
}