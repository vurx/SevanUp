package com.vvvv.sevanUp.study.concurrency.example._4_singleton;

import com.vvvv.studying.concurrency.annoations.ThreadSafe;

/**
 * @ClassName Singleton2
 * @Description 单例模式：饿汉模式。在类创建时建立实例。
 * 存在两个问题：
 * 1：可能会造成资源浪费（类实例化并没有被使用）
 * 2：如果在私有构造方法里面有较多逻辑处理时，会导致类加载特别慢
 * @Author vvvv
 * @Date 2020/5/25 14:03
 * @Version V1.0
 */
@ThreadSafe
public class Singleton2 {
    private Singleton2() {
    }
    private static Singleton2 instance = new Singleton2();

    public static Singleton2 getInstance() {
        return instance;
    }
}