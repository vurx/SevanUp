package com.vvvv.sevanUp.service;

import com.github.pagehelper.PageInfo;
import com.vvvv.sevanUp.model.quartz.JobAndTriggerDto;
import org.quartz.SchedulerException;

/**
 * @Author: CJ
 * @Date: 2021-11-2 14:20
 */
public interface QuartzService {

    PageInfo<JobAndTriggerDto> getJobAndTriggerDetails(Integer pageNum, Integer pageSize);

    void addjob(String jName, String jGroup, String tName, String tGroup, String description, String cron, String user);

    void pausejob(String jName, String jGroupe) throws SchedulerException;

    void resumejob(String jName, String jGroup) throws SchedulerException;

    void rescheduleJob(String jName, String jGroup, String cron, String desc) throws SchedulerException;

    void deletejob(String jName, String jGroup) throws SchedulerException;
}
