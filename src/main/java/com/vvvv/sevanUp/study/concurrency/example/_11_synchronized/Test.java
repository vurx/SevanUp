package com.vvvv.sevanUp.study.concurrency.example._11_synchronized;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;

/**
 * @ClassName Test
 * @Description
 * @Author vvvv
 * @Date 2020/9/18 01:44
 * @Version V1.0
 */
@Slf4j
public class Test {


    public static void main(String[] args) throws InterruptedException {
        HashMap<Integer, Integer> map = new HashMap<>();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                map.put(i, i);
            }
        }).start();







        VVV l = new VVV();
        System.out.println(ClassLayout.parseInstance(l).toPrintable());


//        log.info(ClassLayout.parseInstance(l).toPrintable());
//        Thread t1 = new Thread(()->{
//            lock();
//        });
//        t1.start();
//        t1.join();
//        Thread t2 = new Thread(()->{
//            lock();
//        });
//        t2.start();
//        Thread t3 = new Thread(()->{
//            lock();
//        });
//        t3.start();
    }
//
//    public static void lock() {
//        synchronized (l) {
//            log.info(Thread.currentThread().getName());
//            log.info(ClassLayout.parseInstance(l).toPrintable());
//        }
//    }
}