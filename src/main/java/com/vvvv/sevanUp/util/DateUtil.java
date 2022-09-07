package com.vvvv.sevanUp.util;

import java.text.SimpleDateFormat;

public class DateUtil {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    /**
     * 获取秒级时间戳
     *
     * @return
     */
    public static String currentTimeSeconds() {
        return (System.currentTimeMillis() + "").substring(0, 10);
    }

    public static Long[] convertStampToNormal(Long stamp) {
        Long[] time = new Long[2];
        // 分
        time[0] = stamp / 1000 / 60;
        // 秒
        time[1] = (stamp / 1000) % 60;
        return time;

    }

    public static void main(String[] args) {
        // 59分23秒
        long time = (59 * 60 + 23) * 1000;

        System.out.println(convertStampToNormal(time));
        // 1小时3分钟23秒
        time = (104 * 60 + 23) * 1000;
        System.out.println(convertStampToNormal(time));

    }

}
