package com.vvvv.sevanUp.basic.aspect.annotationAspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DemoAnnotation {

    /**
     * @return 操作编号
     */
    int no() default 0;

    /**
     * @return 模块名称
     */
    String value() default "";

    /**
     * @return 操作名称
     */
    String operation() default "";

    /**
     * @return 是否需要日志打印
     */
    boolean isTrue() default true;
}
