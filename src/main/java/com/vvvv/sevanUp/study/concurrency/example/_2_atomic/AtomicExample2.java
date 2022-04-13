package com.vvvv.sevanUp.study.concurrency.example._2_atomic;

import com.vvvv.studying.concurrency.annoations.ThreadSafe;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName AtomicExample2
 * @Description atomicReference使用类（对多个属性同时进行并发修改保证原子性）
 * @Author vvvv
 * @Date 2020/5/20 10:26
 * @Version V1.0
 */
@ThreadSafe
public class AtomicExample2 {
    private static final Logger log = LoggerFactory.getLogger(AtomicExample2.class);
    private Reference reference;

    private AtomicReference<Reference> atomicReference;

    /**
     * 构建器中初始化AtomicReference
     *
     * @param reference
     */
    public AtomicExample2(Reference reference) {
        this.reference = reference;
        this.atomicReference = new AtomicReference<>(reference);
    }

    public void atomic(Reference reference) {
        Reference referenceOld;
        Reference referenceNew;
        long sequence;
        long timestamp;
        while (true) {
            // 取出内存中的对象
            referenceOld = this.atomicReference.get();
            // 取出内存中的对象的序列
            sequence = referenceOld.getSequence();
            // 序列加1
            sequence++;
            // 获取新的时间戳
            timestamp = System.currentTimeMillis();
            referenceNew = new Reference(sequence, timestamp);
            /**
             * 比较交换 比较old与内存中的值是否相等，相等替换返回ture，不相等false
             */
            if (this.atomicReference.compareAndSet(referenceOld, referenceNew)) {
                reference.setSequence(sequence);
                reference.setTimestamp(timestamp);
                break;
            }
        }
    }
}

@Data
@AllArgsConstructor
class Reference {
    /**
     * 序列
     */
    private long sequence;
    /**
     * 时间戳
     */
    private long timestamp;
}

