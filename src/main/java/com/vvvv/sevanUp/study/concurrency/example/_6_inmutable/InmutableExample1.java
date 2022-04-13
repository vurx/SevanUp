package com.vvvv.sevanUp.study.concurrency.example._6_inmutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName InmutableExample1
 * @Description final修饰变量
 * @Author vvvv
 * @Date 2020/5/26 14:10
 * @Version V1.0
 */
public class InmutableExample1 {
    private final static Logger log = LoggerFactory.getLogger(InmutableExample1.class);

    private final static Integer a = 0;
    private final static String b = "x";
    private final static Map<Integer, Integer> map = new HashMap<>();
    private static  Map<Integer, Integer> mapUndifable = new HashMap<>();

    static {
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
    }
    static {
        mapUndifable.put(1, 1);
        mapUndifable.put(2, 2);
        mapUndifable.put(3, 3);
        mapUndifable = Collections.unmodifiableMap(mapUndifable);
    }

    public static void main(String[] args) {
        // final 修饰的变量 不可以再修改
        // a = 1;
        // b = "v";
        // final修饰的引用变量 只是不允许再次修改引用 但是其内部的值还是可以修改
//         map = new HashMap();
        map.put(1, 4);
        log.info("{}",map.toString());
        // Colections中的unmodefiableXXX 可以控制其内部的值不能被修改 会抛出异常
        mapUndifable.put(1, 2);
        /*
            Exception in thread "main" java.lang.UnsupportedOperationException
                at java.util.Collections$UnmodifiableMap.put(Collections.java:1457)
                at com.vvvv.studying.concurrency.example.inmutable.InmutableExample1.main(InmutableExample1.java:46)
        */

    }

}