package com.vvvv.sevanUp.study.lambda;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestStreamAPI {

    @Test
    public void test() {
        //1.通过Collection系列的集合提供的stream（）
        Map<String, String> obj = new HashMap<String, String>() {{
            put("c", "2");
        }};

        Map<String, String> x = new HashMap<>();
//        x = obj;

        obj.forEach((k,v)->{
            x.put(k, v);
        });

        obj.put("d", "7");
        System.out.println(x);


    }
}
