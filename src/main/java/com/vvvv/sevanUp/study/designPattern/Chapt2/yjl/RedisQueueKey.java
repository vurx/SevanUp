package com.vvvv.sevanUp.study.designPattern.Chapt2.yjl;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisQueueKey {
    String source();
}
