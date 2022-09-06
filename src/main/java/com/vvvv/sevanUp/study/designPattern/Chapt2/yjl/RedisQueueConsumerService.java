package com.vvvv.sevanUp.study.designPattern.Chapt2.yjl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public abstract class RedisQueueConsumerService {

    public abstract void deal();

    public void updateResourceById() {
        log.info("updateResourceById");
    }
}
