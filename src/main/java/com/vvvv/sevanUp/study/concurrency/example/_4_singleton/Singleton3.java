package com.vvvv.sevanUp.study.concurrency.example._4_singleton;

import com.vvvv.studying.concurrency.annoations.ThreadNotSafe;

import java.lang.reflect.Constructor;

/**
 * @ClassName Singleton3
 * @Description 懒汉模式：双重检测机制（多线程中会发生指令重排）
 * @Author vvvv
 * @Date 2020/5/26 09:45
 * @Version V1.0
 */
@ThreadNotSafe
public class Singleton3 {
    private Singleton3() {
    }

    private static Singleton3 instance = null;

    //1：分配对象内存空间
    //2：初始化对象
    //3：设置instance指向刚分配的内存

    //发生指令重排后 顺序1->3->2 单线程没问题 多线程如下

    //线程A和B
    //A走到了34行，但是执行顺序为1->3->2，只进行到了3实际还没初始化对象
    //此时线程B走到了31行，判断instance不为空，直接跳过，导致问题出现
    public static Singleton3 getInstance() {
        if (instance == null) { //双重检测机制
            synchronized (Singleton3.class) { //同步锁
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        Singleton3 s = Singleton3.getInstance();
        Singleton3 sual = Singleton3.getInstance();
        Constructor<Singleton3> constructor = Singleton3.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton3 s2 = constructor.newInstance();
        System.out.println(s + "\n" + sual + "\n" + s2);
        System.out.println("正常情况下，实例化两个实例是否相同：" + (s == sual));
        System.out.println("通过反射攻击单例模式情况下，实例化两个实例是否相同：" + (s == s2));
    }
}