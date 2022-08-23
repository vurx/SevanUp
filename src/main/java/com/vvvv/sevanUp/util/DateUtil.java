package com.vvvv.sevanUp.util;

public class DateUtil {

    /**
     * 获取秒级时间戳
     * @return
     */
    public static String currentTimeSeconds(){
        return (System.currentTimeMillis() + "").substring(0, 10);
    }
}
