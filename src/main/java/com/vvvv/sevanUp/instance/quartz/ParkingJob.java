package com.vvvv.sevanUp.instance.quartz;

import com.vvvv.sevanUp.basic.constant.enums.StringEnum;
import com.vvvv.sevanUp.instance.wechat.messageNotice.WechatMsgContext;
import com.vvvv.sevanUp.service.ParkService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 停车通知定时任务
 */
@Slf4j
@Component
public class ParkingJob implements Job {

    @Autowired
    private WechatMsgContext wechatContext;

    @Autowired
    private ParkService parkService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        jobDetail.getJobDataMap().getString("title");
        Map<String, Object> parkTimeInfo = parkService.getParkTimeInfo();
        Long[] diff = (Long[]) parkTimeInfo.get("diff");
        String title = String.format(StringEnum.WECHAT_PARK_TITLE.getStr(), diff[0], diff[1]);
        Map<String, String> map = new HashMap<>();
        map.put("msgType", "news");
        map.put("toUser", "@all");
        map.put("title", title);
        map.put("description", String.format(StringEnum.WECHAT_PARK_DESCRIPTION.getStr(), parkTimeInfo.get("parkStr"), parkTimeInfo.get("nowStr")));
        map.put("picurl", StringEnum.WECHAT_PARK_PIC.getStr());
        if (diff[0] == 60 || diff[0] == 90) {
            wechatContext.sendMsg(map);
            log.info("{}并已发送通知。", title);

        } else if (diff[0] >= 109 && diff[0] <= 110) {
            title = String.format(StringEnum.WECHAT_PARK_TITLE_CHARGE.getStr(), diff[0], diff[1]);
            map.put("title", title);
            wechatContext.sendMsg(map);
            log.info("{}并已发送通知。", title);
        }
    }
}