package com.vvvv.sevanUp.study.concurrency.howToCreateThread;

import org.junit.Test;

/**
 * @ClassName CreatingThread01
 * @Description 继承Thread类并重写run()方法
 * @Author vvvv
 * @Date 2020/7/22 10:07
 * @Version V1.0
 */
public class CreatingThread01 extends Thread{

    /*
    缺点：
        继承Thread类并重写run()方法，这种方式的弊端是一个类只能继承一个父类，如果这个类本身已经继承了其它类，就不能使用这种方式了。
     */

    @Override
    public void run() {
        System.out.println(getName() + " is running");
    }

    @Test
    public void test(){
        new CreatingThread01().start();
        new CreatingThread01().start();
        new CreatingThread01().start();
        new CreatingThread01().start();
    }
}