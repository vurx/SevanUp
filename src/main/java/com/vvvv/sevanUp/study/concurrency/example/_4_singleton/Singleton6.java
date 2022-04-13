package com.vvvv.sevanUp.study.concurrency.example._4_singleton;


import com.vvvv.sevanUp.study.concurrency.annoations.Recommend;
import com.vvvv.sevanUp.study.concurrency.annoations.ThreadSafe;

/**
 * @ClassName Singleton6
 * @Description 枚举单例
 * @Author vvvv
 * @Date 2020/5/25 15:28
 * @Version V1.0
 */
@ThreadSafe
@Recommend
public class Singleton6 {
    private Singleton6() {
    }

    public static Singleton6 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    public enum Singleton {
        //创建一个枚举对象，该对象天生为单例
        INSTANCE;

        private Singleton6 singleton6;

        // 枚举的构造函数JVM保证了只会被调用一次
        Singleton() {
            singleton6 = new Singleton6();
        }

        public Singleton6 getInstance() {
            return singleton6;
        }
    }
}