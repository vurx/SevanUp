package com.vvvv.sevanUp.quartz;

import com.vvvv.sevanUp.instance.Wechat;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Slf4j
@Component
public class RemindJob implements Job {

    @Autowired
    private Wechat wechat;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        String jobName = jobDetail.getKey().getName();
        String jobGroup = jobDetail.getKey().getGroup();
        String description = jobDetail.getDescription();
        log.info("============定时任务:【{}】，组:【{}】开始============", jobName, jobGroup);
        log.info("模拟触发，还款人：{},还款提醒：{}", jobGroup, description);
        HashMap<String, String> param = new HashMap<String, String>() {{
            put("title", jobName);
            put("description", description);
            put("picurl", null);
        }};
        wechat.sendMsg(param);
        log.info("============定时任务:【{}】，组:【{}】结束============", jobName, jobGroup);

    }
}