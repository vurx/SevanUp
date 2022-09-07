package com.vvvv.sevanUp.instance;

import com.alibaba.fastjson.JSONObject;
import com.vvvv.sevanUp.basic.OutterHttpClient;
import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import com.vvvv.sevanUp.basic.exception.VurxException;
import com.vvvv.sevanUp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Wechat {

    private String token;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private OutterHttpClient outterHttpClient;

    public void refreshToken() {
        String jsonToken = outterHttpClient.getToken();
        JSONObject jsonObject = JSONObject.parseObject(jsonToken);
        token = jsonObject.getString("access_token");
    }

    /**
     * 发送企业微信消息
     *
     * @param param
     */
    public void sendMsg(HashMap<String, String> param) {
        refreshToken();
        Map<String, Object> sendMap = new HashMap<>();
        sendMap.put("touser", "WuSuHuan");
        sendMap.put("msgtype", "news");
        sendMap.put("agentid", "1000002");
        sendMap.put("enable_id_trans", 0);
        sendMap.put("enable_duplicate_check", 0);
        sendMap.put("duplicate_check_interval", 1800);
        Map<String, Object> news = new HashMap<>();
        List<HashMap<String, String>> articles = new ArrayList<HashMap<String, String>>() {{
            add(param);
        }};
        news.put("articles", articles);
        sendMap.put("news", news);
        outterHttpClient.sendWechatMsg(token, sendMap);
    }

    public HashMap<String, String> paramConstruct(String title) {
        Date nowTime = new Date();
        String nowStr = DateUtil.sdf.format(nowTime);
        String parkStr = redisTemplate.opsForValue().get("parkStr");
        Date parkTime = null;
        try {
            parkTime = DateUtil.sdf.parse(parkStr);
        } catch (ParseException e) {
            throw new VurxException(ReturnInfoEnum.TRANSFER_DATESTR_ERROR);
        }
        long l = nowTime.getTime() - parkTime.getTime();
        Long[] diff = DateUtil.convertStampToNormal(l);
        return new HashMap<String, String>() {{
            put("title", String.format(title, diff[0], diff[1]));
            put("description", String.format("入场时间：%s \n通知时间：%s", parkStr, nowStr));
            put("picurl", "https://s3.bmp.ovh/imgs/2022/07/07/65cca3da6ab77929.jpg");
        }};
    }
}
