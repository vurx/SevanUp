package com.vvvv.sevanUp.study.concurrency.example._4_singleton;

import com.vvvv.studying.concurrency.annoations.ThreadSafe;

/**
 * @ClassName Singleton4
 * @Description 懒汉模式：双重检测机制（多线程中会发生指令重排）
 * @Author vvvv
 * @Date 2020/5/26 09:45
 * @Version V1.0
 */
@ThreadSafe
public class Singleton4 {
    private Singleton4() {
    }
    //此处加了volatile关键词，防止指令重排
    private volatile static Singleton4 instance = null;

    public static Singleton4 getInstance() {
        if (instance == null) { //双重检测机制
            synchronized (Singleton4.class){ //同步锁
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}