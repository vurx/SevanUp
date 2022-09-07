package com.vvvv.sevanUp.instance.park;

import com.alibaba.fastjson.JSONObject;
import com.vvvv.sevanUp.basic.OutterHttpClient;
import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import com.vvvv.sevanUp.basic.exception.VurxException;
import com.vvvv.sevanUp.instance.Wechat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class Parking {

    // 停车入场时间
    public Date park;
    public String parkStr, parkPic = "https://s3.bmp.ovh/imgs/2022/07/07/65cca3da6ab77929.jpg";
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    boolean scheduledSwitch = false;

    @Autowired
    private Wechat wechat;

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 停车功能初始化
     * 1.获取通知token
     * 2.从redis获取入场时间
     */
    @PostConstruct // @PostConstruct注解修饰的方法其生命周期位于构造方法调用之后，在Spring属性值注入之前
    public void initParkInfo() {
        if (scheduledSwitch) {
            parkStr = redisTemplate.opsForValue().get("parkStr");
            try {
                park = sdf.parse(parkStr);
                log.info("=======初始化成功=======");
            } catch (ParseException e) {
                throw new VurxException(ReturnInfoEnum.TRANSFER_DATESTR_ERROR);
            }
        }
    }

    /**
     * 定时任务
     * TODO 需要优化定时任务
     */
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void scheduled1() {
        scheduledSwitch = Boolean.parseBoolean(redisTemplate.opsForValue().get("scheduledSwitch"));
        if (scheduledSwitch) {
            if (null != park && null != parkStr && parkPic != null) {
                // 当前时间
                Date now = new Date();
                String nowStr = sdf.format(now);
                long l = now.getTime() - park.getTime();
                long s = (l / 1000) % 60;
                l = l / 1000 / 60;
                String description = "入场时间：" + parkStr + " \n通知时间：" + nowStr;
                if (l >= 110 && l <= 111) {
                    // 快到停车时间
                    String title = "您已停车" + l + "分钟" + s + "秒，即将收费！";
                    HashMap<String, String> param = new HashMap<String, String>() {{
                        put("title", title);
                        put("description", description);
                        put("picurl", parkPic);
                        put("url", parkPic);
                    }};
                    wechat.sendMsg(param);
                } else if (l == 60 || l == 90) {
                    log.info("已经停车{}分钟{}秒", l, s);
                    String title = "您已停车" + l + "分钟" + s + "秒！";
                    HashMap<String, String> param = new HashMap<String, String>() {{
                        put("title", title);
                        put("description", description);
                        put("picurl", parkPic);
                        put("url", parkPic);
                    }};
                    wechat.sendMsg(param);
                }
                log.info("已经停车{}分钟{}秒", l, s);
            }
        }
    }
}
