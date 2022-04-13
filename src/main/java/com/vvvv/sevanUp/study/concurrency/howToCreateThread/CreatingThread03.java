package com.vvvv.sevanUp.study.concurrency.howToCreateThread;

import org.junit.Test;

/**
 * @ClassName CreatingThread03
 * @Description 匿名内部类
 * @Author vvvv
 * @Date 2020/7/22 10:15
 * @Version V1.0
 */
public class CreatingThread03 {
    @Test
    public void test(){
        //Thread匿名类
        new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + " is running(Thread)");
            }
        }.start();

        // Runnable匿名类，实现其run()方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running(Runnalbe)");
            }
        }).start();

        // 同上，使用lambda表达式函数式编程
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " is running(lambda)");
        }).start();
    }
}