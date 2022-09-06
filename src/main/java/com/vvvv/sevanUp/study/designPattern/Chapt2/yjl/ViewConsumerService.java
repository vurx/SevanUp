package com.vvvv.sevanUp.study.designPattern.Chapt2.yjl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("ViewConsumerService")
@RedisQueueKey(source = "views")
@Slf4j
public class ViewConsumerService extends RedisQueueConsumerService{
    @Override
    public void deal() {
        updateResourceById();
        log.info("浏览量更新");
    }
}
