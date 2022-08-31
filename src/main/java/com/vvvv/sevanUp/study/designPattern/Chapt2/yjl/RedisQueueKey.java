package com.vvvv.sevanUp.study.designPattern.Chapt2.yjl;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface RedisQueueKey {
    String source();
}
