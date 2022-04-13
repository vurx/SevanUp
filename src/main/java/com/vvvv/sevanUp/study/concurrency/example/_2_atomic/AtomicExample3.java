package com.vvvv.sevanUp.study.concurrency.example._2_atomic;

import com.vvvv.studying.concurrency.annoations.ThreadSafe;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @ClassName AtomicExample3
 * @Description AtomicReferenceFieldUpdater使用类（用来将指定类型的指定的volatile引用字段进行原子更新，对应的原子引用字段不能是private的。）
 * @Author vvvv
 * @Date 2020/5/20 10:26
 * @Version V1.0
 */
@ThreadSafe
public class AtomicExample3 {
    private static final Logger log = LoggerFactory.getLogger(AtomicExample3.class);

    static AtomicReferenceFieldUpdater update = AtomicReferenceFieldUpdater.newUpdater(Dog.class, String.class, "name");

    public static void main(String[] args) {
        Dog dog = new Dog();
        log.info("result:{}", update.compareAndSet(dog, "nacy", "compareAndSet")); // true
        log.info("result:{}", dog.name); // compareAndSet
        log.info("result:{}", update.getAndSet(dog, "getAndSet")); // compareAndSet
        log.info("result:{}",dog.name); //getAndSet
    }
}

@Data
class Dog {
    public volatile String name = "nacy";
    private int age;
}

