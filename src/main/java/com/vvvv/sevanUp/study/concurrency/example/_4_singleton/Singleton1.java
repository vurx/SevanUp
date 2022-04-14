package com.vvvv.sevanUp.study.concurrency.example._4_singleton;

import com.vvvv.sevanUp.study.concurrency.annoations.ThreadNotSafe;

/**
 * @ClassName Singleton1
 * @Description 单例模式：懒汉模式。单例实例在第一次使用时进行创建
 * 多线程操作时，存在两个线程以上时，同时进入判断方法判为空，即new了两个对象。
 * @Author vvvv
 * @Date 2020/5/25 10:30
 * @Version V1.0
 */
@ThreadNotSafe
public class Singleton1 {
    // 私有化构造器--该类无法被new
    private Singleton1() {
    }
    public int a;

    private static Singleton1 instance = null;

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }

}