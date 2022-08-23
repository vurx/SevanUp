package com.vvvv.sevanUp.instance.park;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;
import com.vvvv.sevanUp.basic.OutterHttpClient;
import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import com.vvvv.sevanUp.basic.exception.VurxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private OutterHttpClient outterHttpClient;

    private String token;

    @Autowired
    private StringRedisTemplate redisTemplate;

    // @PostConstruct注解修饰的方法其生命周期位于构造方法调用之后，在Spring属性值注入之前
    @PostConstruct
    public void initParkInfo() {
//        String orderNO = redisTemplate.opsForValue().get("orderNo");
//        log.info("orderNO={}", orderNO);
//        if (orderNO != null) {
//            String result = outterHttpClient.getParkMsg(orderNO);
//            if (!result.contains("<br><br> 订单未找到 <br><br>")) {
//                parkStr = result.substring(result.indexOf("<dl><dd>入场时间</dd><dd>") + 21, result.indexOf("<dl><dd>入场时间</dd><dd>") + 40);
//                parkPic = result.substring(result.indexOf("data-img=\"") + 10, result.indexOf(" style=\"background-image:url('../../Static/img/camera.svg');") - 1).replace("&amp;", "&");
//                log.info("车辆入场时间为：{}", parkStr);
//                log.info("车辆入场图片为：{}", parkPic);
//                try {
//                    park = sdf.parse(parkStr);
//                    log.info("=======初始化成功=======");
//                } catch (ParseException e) {
//                    throw new VurxException(ReturnInfoEnum.TRANSFER_DATESTR_ERROR);
//                }
//            } else {
//                log.info("=======初始化失败，订单未找到=======");
//
//            }
//        } else {
//            log.info("=======初始化失败，orderNow为null=======");
//        }
        String jsonToken = outterHttpClient.getToken();
        JSONObject jsonObject = JSONObject.parseObject(jsonToken);
        token  = jsonObject.getString("access_token");
        parkStr = redisTemplate.opsForValue().get("parkStr");
        try {
            park = sdf.parse(parkStr);
            log.info("=======初始化成功=======");
        } catch (ParseException e) {
            throw new VurxException(ReturnInfoEnum.TRANSFER_DATESTR_ERROR);
        }
    }

    /**
     * 定时任务
     * TODO 需要优化定时任务
     */
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void scheduled1() {
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
                HashMap<String, String> param = new HashMap<String,String>() {{
                    put("title", title);
                    put("description", description);
                    put("picurl", parkPic);
                    put("url", parkPic);
                }};
                sendMsg(param);
            } else if (l == 60 || l == 90) {
                log.info("已经停车{}分钟{}秒", l, s);
                String title = "您已停车" + l + "分钟" + s + "秒！";
                HashMap<String, String> param = new HashMap<String,String>() {{
                    put("title", title);
                    put("description", description);
                    put("picurl", parkPic);
                    put("url", parkPic);
                }};
                sendMsg(param);
            }
            log.info("已经停车{}分钟{}秒", l, s);
        }
    }


    /**
     * 发送企业微信消息
     *
     * @param param
     */
    public void sendMsg(HashMap<String, String> param) {
        Map<String, Object> sendMap = new HashMap<>();
        sendMap.put("touser", "@all");
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

    /**
     * 发送钉钉消息
     *
     * @param time
     * @param second
     * @param now
     */
    public void dingTalk(long time, long second, String now) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=af60114dc38541c19657126aee5c8a3ce108969c87085a1b7fbae91b5df40889");
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("挪车啦挪车啦！！！");
        markdown.setText("# 目前停车时长：" + time + "分钟" + second + "秒" +
                "\n > 入场时间为：" + parkStr + "\n" +
                "\n > ![screenshot](" + parkPic + ")\n > 通知时间为：" + now);
        request.setMarkdown(markdown);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll(true);
        request.setAt(at);
        try {
            client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

}
