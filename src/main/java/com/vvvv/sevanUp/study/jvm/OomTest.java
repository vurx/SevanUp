package com.vvvv.sevanUp.study.jvm;

public class OomTest {

    public static void main(String[] args) {
        System.out.println("start");

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
