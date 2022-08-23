package com.vvvv.sevanUp.controller;

import com.vvvv.sevanUp.instance.park.Parking;
import com.vvvv.sevanUp.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@RestController
@Slf4j
public class ParkController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Parking parking;

    /**
     * 修改停车入场时间
     * @param time
     * @return
     */
    @GetMapping("/set")
    public String test2(@RequestParam("time") String time) {
        // 将传入的221100，用：分隔
        String newStr = Optional.ofNullable(StringUtil.join(time.substring(0, 2), time.substring(2, 4), time.substring(4, 6))).orElse("");
        // 获取当前时间年月日，拼接上面的newStr
        Date now = new Date();
        String nowStr = parking.sdf.format(now);
        nowStr = nowStr.substring(0, 11).concat(newStr);
        // 存入redis
        redisTemplate.opsForValue().set("parkStr", nowStr);
        // 定时任务开关打开
        redisTemplate.opsForValue().set("scheduledSwitch","true");
        // 停车功能初始化
        parking.initParkInfo();
        log.info("停车时间更新成功：{}", nowStr);
        return "ok";
    }

    /**
     * 手动触发停车通知
     * @return
     */
    @GetMapping("/hand")
    public String hand() {
        parking.initParkInfo();
        // 当前时间
        Date now = new Date();
        String nowStr = parking.sdf.format(now);
        long l = now.getTime() - parking.park.getTime();
        long s = (l / 1000) % 60;
        l = l / 1000 / 60;
        String description = "入场时间：" + parking.parkStr + " \n通知时间：" + nowStr;
        // 快到停车时间
        String title = "您已停车" + l + "分钟" + s + "秒！（手动触发）";
        HashMap<String, String> param = new HashMap<String, String>() {{
            put("title", title);
            put("description", description);
            put("picurl", parking.parkPic);
            put("url", parking.parkPic);
        }};
        parking.sendMsg(param);
        return "ok";
    }

    @GetMapping("/stop")
    public String test2() {
        // 定时任务开关打开
        redisTemplate.opsForValue().set("scheduledSwitch","false");
        return "ok";
    }
}