package com.vvvv.sevanUp.study.designPattern.Chapt2.yjl;

import com.vvvv.sevanUp.basic.aspect.annotationAspect.CustomCut;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RedisConsumerRunner implements ApplicationRunner {

    String[] keys = {
            "views",
            "download"
    };

    private Map<String, RedisQueueConsumerService> redisKQueueKeyMap;

    /**
     * 填充redisKQueueKeyMap对象
     * （extends=RedisQueueConsumerService抽象类的继承类们）
     * k：每个extends的RedisQueueKey注解
     * v：对应的extend
     *
     * @param redisKQueueKeys
     */
    @Autowired
    public void setRedisKQueueKeyMap(List<RedisQueueConsumerService> redisKQueueKeys) {
        // 注入各种类型的实现类
        redisKQueueKeyMap = redisKQueueKeys.stream().collect(
                Collectors.toMap(t -> AnnotationUtils.findAnnotation(t.getClass(), RedisQueueKey.class).source(),
                        v -> v, (v1, v2) -> v1));
    }

    @CustomCut(aspect = {"Around", "AfterThrowing"}, describe = "RedisConsumerRunner")
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("test,,,,,,,,,,,,");
        /*ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < keys.length; i++) {
            RedisConsumer redisConsumer = new RedisConsumer(keys[i]);
            pool.submit(redisConsumer);
        }*/
    }


    @AllArgsConstructor
    class RedisConsumer implements Runnable {
        private String key;

        @Override
        public void run() {
            redisKQueueKeyMap.get(key).deal();
        }
    }
}