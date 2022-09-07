package com.vvvv.sevanUp.quartz;

import com.vvvv.sevanUp.basic.constant.enums.ReturnInfoEnum;
import com.vvvv.sevanUp.basic.exception.VurxException;
import com.vvvv.sevanUp.instance.Wechat;
import com.vvvv.sevanUp.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 停车通知定时任务
 */
@Slf4j
@Component
public class ParkingJob implements Job {

    @Autowired
    private Wechat wechat;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
            JobDetail jobDetail = jobExecutionContext.getJobDetail();
            String description = jobDetail.getDescription();
            HashMap<String, String> stringStringHashMap = wechat.paramConstruct(description);
//            log.info(title);
//            if (diff[0] == 60 || diff[0] == 90 || (diff[0] >= 109 && diff[0] <= 110)) {
//                HashMap<String, String> param = new HashMap<String, String>() {{
//                    put("title", title);
//                    put("description", info);
//                    put("picurl", "https://s3.bmp.ovh/imgs/2022/07/07/65cca3da6ab77929.jpg");
//                }};
//                wechat.sendMsg(param);
//            }

    }

    public static void main(String[] args) {


    }

}