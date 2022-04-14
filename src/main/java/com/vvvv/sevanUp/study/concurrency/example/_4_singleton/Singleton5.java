package com.vvvv.sevanUp.study.concurrency.example._4_singleton;

import com.vvvv.sevanUp.study.concurrency.annoations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName Singleton5
 * @Description 单例模式：饿汉模式。在类创建时建立实例。
 * 存在两个问题：
 * 1：可能会造成资源浪费（类实例化并没有被使用）
 * 2：如果在私有构造方法里面有较多逻辑处理时，会导致类加载特别慢
 * @Author vvvv
 * @Date 2020/5/25 14:03
 * @Version V1.0
 */
@ThreadSafe
public class Singleton5 {
    private static final Logger log = LoggerFactory.getLogger(Singleton5.class);

    private Singleton5() {
    }

    private static Singleton5 instance = null;

    static {
        instance = new Singleton5();
    }

    public static Singleton5 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        // 静态域在静态块前为有值
        // 静态块在静态域前为null
        log.info("{}",getInstance());
        log.info("{}",getInstance());
    }
}