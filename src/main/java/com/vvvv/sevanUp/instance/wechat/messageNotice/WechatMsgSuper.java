package com.vvvv.sevanUp.instance.wechat.messageNotice;

import com.alibaba.fastjson.JSONObject;
import com.vvvv.sevanUp.basic.OutterHttpClient;
import com.vvvv.sevanUp.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 企业微信的发送消息抽象类
 */
@Component
public abstract class WechatMsgSuper {

    @Autowired
    private OutterHttpClient outterHttpClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 获取token
     */
    private String acceptToken() {
        // 1.从redis中获取token
        String wechatToken = redisTemplate.opsForValue().get("wechat_token");
        // 如果获取不到则需要重新获取
        if (StringUtil.isEmpty(wechatToken)) {
            String jsonToken = outterHttpClient.getToken();
            JSONObject jsonObject = JSONObject.parseObject(jsonToken);
            wechatToken = jsonObject.getString("access_token");
            // 放入redis，有效期为7200s
            redisTemplate.opsForValue().set("wechat_token", wechatToken,7200, TimeUnit.SECONDS);

        }
        return wechatToken;
    }

    /**
     * 构造消息的主体的抽象方法
     * 实现类：不同的消息类型，eg:news text ....
     * @param param
     * @return
     */
    public abstract HashMap<String, Object> paramConstruct(Map<String, String> param);

    /**
     * 发送消息
     * @param param
     */
    public void sendNews(Map<String, Object> param) {
        outterHttpClient.sendWechatMsg(acceptToken(), param);
    }



}
