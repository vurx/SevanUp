package com.vvvv.sevanUp.mapper.quartz;

import com.vvvv.sevanUp.model.quartz.JobAndTriggerDto;
import com.vvvv.sevanUp.model.quartz.JobDetail;
import com.vvvv.sevanUp.model.quartz.JobDetailKey;
import org.apache.ibatis.annotations.Mapper;



import java.util.List;

@Mapper
public interface JobDetailMapper {
    int deleteByPrimaryKey(JobDetailKey key);

    int insert(JobDetail record);

    int insertSelective(JobDetail record);

    JobDetail selectByPrimaryKey(JobDetailKey key);

    int updateByPrimaryKeySelective(JobDetail record);

    int updateByPrimaryKeyWithBLOBs(JobDetail record);

    int updateByPrimaryKey(JobDetail record);

    List<JobAndTriggerDto> getJobAndTriggerDetails();
}