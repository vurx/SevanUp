package com.vvvv.sevanUp.service;

import com.vvvv.sevanUp.basic.constant.enums.StringEnum;
import com.vvvv.sevanUp.instance.wechat.messageNotice.WechatMsgContext;
import com.vvvv.sevanUp.util.DateUtil;
import com.vvvv.sevanUp.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ParkService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private WechatMsgContext wechatContext;


    /**
     * 更新停车时间
     * @param time
     */
    public void set(String time) {
        // 将传入的221100，用：分隔
        String newStr = Optional.ofNullable(
                StringUtil.join(
                        time.substring(0, 2),
                        time.substring(2, 4),
                        time.substring(4, 6)
                )
        ).orElse("00:00:00");
        // 获取当前时间年月日，拼接上面的newStr
        String nowStr = DateUtil.getCurrentTimeStr(DateUtil.fullFormat1).substring(0, 11).concat(newStr);
        // 存入redis
        redisTemplate.opsForValue().set("parkStr", nowStr);
        log.info("停车时间更新成功：{}", nowStr);
    }

    /**
     * 手动触发停车通知
     */
    public void send() {
        Map<String, Object> parkTimeInfo = getParkTimeInfo();
        Long[] diff = (Long[]) parkTimeInfo.get("diff");
        wechatContext.sendMsg(new HashMap<String, String>() {{
            put("msgType", "news");
            put("toUser", "WuSuHuan");
            put("title", String.format(StringEnum.WECHAT_PARK_TITLE_HAND.getStr(), diff[0], diff[1]));
            put("description", String.format(StringEnum.WECHAT_PARK_DESCRIPTION.getStr(), parkTimeInfo.get("parkStr"), parkTimeInfo.get("nowStr")));
            put("picurl", StringEnum.WECHAT_PARK_PIC.getStr());
        }});
    }

    /**
     * 获取停车时间信息
     * @return diff:Long[],parkStr:str，nowStr:str
     */
    public Map<String,Object> getParkTimeInfo(){
        String parkStr = redisTemplate.opsForValue().get("parkStr");
        String nowStr = DateUtil.getCurrentTimeStr(DateUtil.fullFormat1);
        Long strTimeDiff = DateUtil.getStrTimeDiff(DateUtil.fullFormat1, nowStr, parkStr);
        return new HashMap<String, Object>() {{
            put("diff", DateUtil.convertStampToNormal(strTimeDiff));
            put("parkStr", parkStr);
            put("nowStr", nowStr);
        }};
    }
}
