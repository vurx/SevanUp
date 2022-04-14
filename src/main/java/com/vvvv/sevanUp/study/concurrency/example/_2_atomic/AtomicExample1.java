package com.vvvv.sevanUp.study.concurrency.example._2_atomic;

import com.vvvv.sevanUp.study.concurrency.annoations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName CountExample2
 * @Description atomic使用类
 * @Author vvvv
 * @Date 2020/5/20 10:26
 * @Version V1.0
 */
@ThreadSafe
public class AtomicExample1 {
    private static final Logger log = LoggerFactory.getLogger(AtomicExample1.class);

    //客户端请求的总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 20;

    //安全原因：CAS compareAndSwap （拿入参值比对内存中的值，如果不等，就一直比较，相等替换计算后的值）
    private static AtomicInteger count = new AtomicInteger(0);
    // atomicLong 和 longAdder 区别：
    // 在低并发时，通过对base的直接更新，可以保障和atomic性能基本一致
    // 在高并发时，通过热点数据分离，在atomicLong基础上将单点的更新压力，分散到各个节点上提高了性能
    // 缺点，在统计时，可能有误差（如全局序列，全局唯一为atomic）
    private static void add() {
        count.incrementAndGet();
        /*
            atomic中incrementAndGet使用unsafe的类中的getAndAddInt方法，源码如下

            public final int getAndAddInt(Object var1, long var2, int var4) {
                int var5;
                do {
                    var5 = this.getIntVolatile(var1, var2);
                } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

                return var5;
            }

            var1为count这个对象
            var2为count这个对象的当前值
            var4为1
            var5为内存中的值

            compareAndSwapInt 是native方法，意思为java底层方法
            判断var1这个对象的当前值（var2）和内存中的值（var5）是否相等，相等即可替换计算后的值（var5+var4）
        */

    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executor.shutdown();
        log.info("count:{}", count.get());
    }
}
