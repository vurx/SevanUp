package com.vvvv.sevanUp.study.designPattern.Chapt2.yjl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.util.AnnotationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RedisConsumerRunner implements ApplicationRunner {

    String[] keys = {
            "views",
            "downloads"
    };

    private Map<String, RedisQueueConsumerService> redisKQueueKeyMap;

    @Autowired
    public void setRedisKQueueKeyMap(List<RedisQueueConsumerService> redisKQueueKeys) {
        // 注入各种类型的实现类
        redisKQueueKeyMap = redisKQueueKeys.stream().collect(
                Collectors.toMap(t -> AnnotationUtils.findAnnotation(t.getClass(), RedisQueueKey.class).source(),
                        v -> v, (v1, v2) -> v1));
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        /**
         * 启动五个线程处理对应队列的消费
         */
        ExecutorService pool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            log.info(key);
//            RedisConsumer redisConsumer = new RedisConsumer(key, true);
//            pool.submit(redisConsumer);
        }

    }

//    @Getter
//    @Setter
//    @AllArgsConstructor
//    class RedisConsumer implements Runnable {
//        private String key;
//        private boolean yes;
//
//        @Override
//        public void run() {
//            while (yes) {
//                redisKQueueKeyMap.get(key).deal(redisQueueReq);
//
//
//            }
//        }
//    }
}