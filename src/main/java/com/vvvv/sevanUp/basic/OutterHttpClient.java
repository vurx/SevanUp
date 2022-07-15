package com.vvvv.sevanUp.basic;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Var;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 企业微信机器人通知client
 */
@Component
public interface OutterHttpClient {
    @Post(
            url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token={token}",
            headers = {"Accept-Charset: utf-8", "Content-Type: application/json"},
            dataType = "json"
    )
    void sendWechatMsg(@Var("token") String token, @JSONBody Map<String, Object> body);

    @Get(
            url = "http://wx.ymiot.net/SCPay/OrderPay/?orderNo={orderNo}&parkKey=65wambv5",
            headers = {"Accept-Charset: utf-8", "Cookie: userNo=3n%2Fgh7ugPQ7562iyp3clwECH5qCOFwSqxFwnaaPYwGw%3D"}
    )
    String getParkMsg(@Var("orderNo") String orderNo);

    @Get(
            url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww99d788dc16c357a8&corpsecret=VuuATYHR7_n5JSuLo_2EkILgbj1Ag75TwTAhORNb5cs"
    )
    String getToken();
}