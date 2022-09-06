package com.vvvv.sevanUp.quartz;

import com.github.pagehelper.PageInfo;
import com.vvvv.sevanUp.model.quartz.JobAndTriggerDto;
import com.vvvv.sevanUp.service.QuartzService;
import com.vvvv.sevanUp.util.SpringUtil;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.context.ApplicationContext;

@Slf4j
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        // 这里为什么要手动加载类
        QuartzService quartzService = (QuartzService) SpringUtil.getBean("quartzServiceImpl");
        PageInfo<JobAndTriggerDto> jobAndTriggerDetails = quartzService.getJobAndTriggerDetails(1, 10);
        log.info("任务列表总数为：" + jobAndTriggerDetails.getTotal());
        log.info("Hello Job执行时间: " + DateUtil.now());
    }
}