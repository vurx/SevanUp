package com.vvvv.sevanUp.study.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * @Description 用来表示【不推荐】的类的注解
 * @Author vvvv
 * @Date 09:31 2020/5/20
 * @Param 
 * @return 
 **/
//目标:给一个类做注解
@Target(ElementType.TYPE)
//注解存在的范围，编译时丢弃
@Retention(RetentionPolicy.SOURCE)
public @interface NotRecommend {
    String value() default "";
}
