package com.vvvv.sevanUp.study.concurrency.howToCreateThread;

import org.junit.Test;

/**
 * @ClassName CreatingThread02
 * @Description 实现Runnable接口
 * @Author vvvv
 * @Date 2020/7/22 10:07
 * @Version V1.0
 */
public class CreatingThread02 implements Runnable{

    /*
    优缺点：
        实现Runnable接口，这种方式的好处是一个类可以实现多个接口，不影响其继承体系。
     */
    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
    }

    @Test
    public void test(){
        new Thread(new CreatingThread02()).start();
        new Thread(new CreatingThread02()).start();
        new Thread(new CreatingThread02()).start();
        new Thread(new CreatingThread02()).start();
        new Thread(new CreatingThread02()).start();
    }
}