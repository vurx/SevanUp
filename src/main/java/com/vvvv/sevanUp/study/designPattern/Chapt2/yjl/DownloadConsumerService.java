package com.vvvv.sevanUp.study.designPattern.Chapt2.yjl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("DownloadConsumerService")
@RedisQueueKey(source = "download")
@Slf4j
public class DownloadConsumerService extends RedisQueueConsumerService{
    @Override
    public void deal(RedisQueueKey redisQueueKey) {
        updateResourceById();
        log.info("下载量更新");
    }
}
