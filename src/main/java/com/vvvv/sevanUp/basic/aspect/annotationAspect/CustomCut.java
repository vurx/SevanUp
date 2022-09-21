package com.vvvv.sevanUp.basic.aspect.annotationAspect;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomCut {
    /**
     * Before -- @Before：前置增强
     * After -- @After：后置增强—增强anyway
     * Around -- @Around：环绕增强
     * AfterReturning -- @AfterReturning：后置增强—方法正常退出时执行
     * AfterThrowing -- @AfterThrowing：后置增强—方法异常执行
     *
     * @return 切面方式
     */
    String[] aspect() default {"AfterThrowing"};

    /**
     * 切面日志描述
     * @return
     */
    String describe() default "";
}
