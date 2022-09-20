package com.vvvv.sevanUp.mapper.quartz;

import com.vvvv.sevanUp.model.quartz.Trigger;
import com.vvvv.sevanUp.model.quartz.TriggerKey;
import org.apache.ibatis.annotations.Mapper;


public interface TriggerMapper {
    int deleteByPrimaryKey(TriggerKey key);

    int insert(Trigger record);

    int insertSelective(Trigger record);

    Trigger selectByPrimaryKey(TriggerKey key);

    int updateByPrimaryKeySelective(Trigger record);

    int updateByPrimaryKeyWithBLOBs(Trigger record);

    int updateByPrimaryKey(Trigger record);
}