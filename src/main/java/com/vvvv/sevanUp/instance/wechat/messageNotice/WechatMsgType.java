package com.vvvv.sevanUp.instance.wechat.messageNotice;


import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WechatMsgType {
    String source();
}
