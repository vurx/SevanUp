package com.vvvv.sevanUp.study.concurrency.example._8_common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class VectorExample1 {
    private final static Logger log = LoggerFactory.getLogger(VectorExample1.class);
    private static Vector<Integer> vecNo = new Vector<>();


    public static void main(String[] args) {
        List<Integer> vec = Collections.synchronizedList(vecNo);
        int num = 0;
        while (num < 10000) {
            for (int i = 0; i < 10; i++) {
                vec.add(i);
            }
            num++;
            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = vec.size() - 1; i >= 0; i--) {
                        vec.remove(i);
                    }
                }
            };
            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < vec.size(); i++) {
                            vec.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }




//        List<Integer> vec = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            vec.add(i);
//        }
//        for (int i = 0; i < vec.size(); i++) {
//            vec.remove(i);
//        }
//        System.out.println(vec.size());

    }
}
