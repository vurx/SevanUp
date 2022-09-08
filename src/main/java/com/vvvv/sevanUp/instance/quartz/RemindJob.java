package com.vvvv.sevanUp.instance.quartz;

import com.vvvv.sevanUp.instance.wechat.messageNotice.WechatMsgContext;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class RemindJob implements Job {

    @Autowired
    private WechatMsgContext wechatContext;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        String jobName = jobDetail.getKey().getName();
        String[] nameArray = jobName.split("_");
        jobName = nameArray[0];
        String jobGroup = jobDetail.getKey().getGroup();
        String description = jobDetail.getDescription();
        Map<String, String> map = new HashMap<>();
        map.put("msgType", "news");
        map.put("toUser", jobGroup);
        map.put("title", jobName);
        map.put("description", description);
        wechatContext.sendMsg(map);
    }
}