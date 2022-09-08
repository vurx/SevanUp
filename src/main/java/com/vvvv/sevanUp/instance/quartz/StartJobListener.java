package com.vvvv.sevanUp.instance.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * 初始化固定定时任务
 * ContextRefreshedEvent：所有bean加载容器结束后触发
 */
@Component
public class StartJobListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            // 停车计时器任务
            TriggerKey triggerKey = TriggerKey.triggerKey("park_trigger", "park");
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .withSchedule(CronScheduleBuilder.cronSchedule("0 */1 * * * ?"))
                        .build();
                JobDetail build = JobBuilder.newJob(ParkingJob.class)
                        .withIdentity("park_job", "park")
                        .build();
                scheduler.scheduleJob(build, trigger);
                scheduler.start();
            }

        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
