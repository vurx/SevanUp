package com.vvvv.sevanUp.study.concurrency.howToCreateThread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName CreatingThread05
 * @Description 定时器（java.util.Timer）
 * @Author vvvv
 * @Date 2020/7/22 11:02
 * @Version V1.0
 */
public class CreatingThread05 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        //TimerTask实际上实现了Runnable接口
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        },0,1000);
    }
}