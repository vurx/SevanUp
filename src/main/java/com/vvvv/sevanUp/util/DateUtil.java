package com.vvvv.sevanUp.util;

import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import com.vvvv.sevanUp.basic.exception.VurxException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public final static String fullFormat1 = "yyyy/MM/dd HH:mm:ss";


    /**
     * 获取秒级时间戳
     *
     * @return
     */
    public static String currentTimeSeconds() {
        return (System.currentTimeMillis() + "").substring(0, 10);
    }

    public static Long currentTimeMilliSeconds() {
        return System.currentTimeMillis();
    }

    public static Long[] convertStampToNormal(Long stamp) {
        Long[] time = new Long[2];
        // 分
        time[0] = stamp / 1000 / 60;
        // 秒
        time[1] = (stamp / 1000) % 60;
        return time;
    }

    public static String getCurrentTimeStr(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 获取两个时间(String)之间的差值
     * @param format 转换格式
     * @param time1 时间1
     * @param time2 时间2
     * @return 时间差
     */
    public static Long getStrTimeDiff(String format, String time1, String time2) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Long v = 0L;
        try {
            v = sdf.parse(time1).getTime() -  sdf.parse(time2).getTime();
        } catch (ParseException e) {
            throw new VurxException(ReturnInfoEnum.TRANSFER_DATESTR_ERROR);
        }
        return Math.abs(v);
    }

}
