package com.vvvv.sevanUp.study.concurrency.example._11_synchronized;

/**
 * @ClassName Aobing
 * @Description
 * @Author vvvv
 * @Date 2020/9/22 16:35
 * @Version V1.0
 */
public class volatileExample {

//    volatile static boolean flag = false; // 线程1的修改变量 线程2可见
    static boolean flag = false; // 不可见 死循环

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println("线程1开始执行");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1结束，并成功修改flag = " + changeFlag());

        });
        t1.start();

        new Thread(() -> {
            System.out.println("线程2开始执行，并获取flag = " + getFlag());
            for(;;){
                if (getFlag()){
                    System.out.println("线程2成功进入");
                    break;
                }
            }
            System.out.println("线程2结束");

        }).start();
    }

    static boolean changeFlag() {
        flag = true;
        return flag;
    }

    static boolean getFlag() {
        return flag;
    }
}