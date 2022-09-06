package com.vvvv.sevanUp.mapper.quartz;


import com.vvvv.sevanUp.model.quartz.CronTrigger;
import com.vvvv.sevanUp.model.quartz.CronTriggerKey;

public interface CronTriggerMapper {
    int deleteByPrimaryKey(CronTriggerKey key);

    int insert(CronTrigger record);

    int insertSelective(CronTrigger record);

    CronTrigger selectByPrimaryKey(CronTriggerKey key);

    int updateByPrimaryKeySelective(CronTrigger record);

    int updateByPrimaryKey(CronTrigger record);
}