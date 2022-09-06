package com.vvvv.sevanUp.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;

@Slf4j
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        /*
         *{
         * 	"concurrentExectionDisallowed": false,
         * 	"durable": false,
         * 	"fullName": "提醒v.vvvv",
         * 	"group": "提醒v",
         * 	"jobBuilder": {},
         * 	"jobClass": "com.vvvv.sevanUp.quartz.HelloJob",
         * 	"jobDataMap": {},
         * 	"name": "vvvv",
         * 	"persistJobDataAfterExecution": false
         * }
         */
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        String jobName = key.getName();
        String jobGroup = key.getGroup();
        log.info("============定时任务:【{}】，组:【{}】开始执行============", jobName, jobGroup);


    }
}