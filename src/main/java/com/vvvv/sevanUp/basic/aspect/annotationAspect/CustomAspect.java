package com.vvvv.sevanUp.basic.aspect.annotationAspect;

import com.vvvv.sevanUp.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.BiFunction;

@Aspect
@Component
@Slf4j
public class CustomAspect {

    /**
     * 注解的aspect字段值匹配传入的flag返回该注解的desc字段值，否则返回null
     */
    BiFunction<CustomCut, String, String> func = (cut, flag) -> {
        if (Arrays.asList(cut.aspect()).contains(flag)) {
            return cut.describe();
        }
        return null;
    };

    /**
     * 只要使用了CustomCut注解就会进入次切面
     */
    @Pointcut("@annotation(cut)")
    public void customCutPoint(CustomCut cut) {}


    /**
     * 根据注解的aspect字段判断是否等于Before进入决定是否进入该切面
     * @param cut 方法注解
     */
    @Before("customCutPoint(cut)")
    public void before(CustomCut cut) {
        String aspectDesc = func.apply(cut, "Before");
        if (StringUtil.isNotEmpty(aspectDesc)) {
            log.info("=====================[start] " + aspectDesc + "=====================");
        }
    }


    /**
     * 根据注解的aspect字段判断是否等于After进入决定是否进入该切面
     * @param cut 方法注解
     */
    @After("customCutPoint(cut)")
    public void after(CustomCut cut) {
        String aspectDesc = func.apply(cut, "After");
        if (StringUtil.isNotEmpty(aspectDesc)) {
            log.info("=====================[end] " + aspectDesc + "=====================");
        }
    }

    /**
     * 根据注解的aspect字段判断是否等于Around进入决定是否进入该切面
     * @param joinPoint 切入实体
     * @param cut 方法注解
     * @throws Throwable 异常抛出
     */
    @Around("customCutPoint(cut)")
    public void around(ProceedingJoinPoint joinPoint,CustomCut cut) throws Throwable {
        String aspectDesc = func.apply(cut, "Around");
        if (StringUtil.isNotEmpty(aspectDesc)) {
            log.info("=====================[around start] " + aspectDesc + "=====================");
            joinPoint.proceed();
            log.info("=====================[around end] " + aspectDesc + "=====================");
        }
    }

    /**
     * 根据注解的aspect字段判断是否等于AfterThrowing进入决定是否进入该切面
     * @param cut 方法注解
     */
    @AfterThrowing("customCutPoint(cut)")
    public void afterThrowing(CustomCut cut) {
        String aspectDesc = func.apply(cut, "AfterThrowing");
        if (StringUtil.isNotEmpty(aspectDesc)) {
            log.info("=====================[exception] " + aspectDesc + "=====================");
        }
    }

    /**
     * 根据注解的aspect字段判断是否等于AfterReturning进入决定是否进入该切面
     * @param cut 方法注解
     */
    @AfterReturning("customCutPoint(cut)")
    public void afterReturning(CustomCut cut) {
        String aspectDesc = func.apply(cut, "AfterReturning");
        if (StringUtil.isNotEmpty(aspectDesc)) {
            log.info("=====================[return] " + aspectDesc + "=====================");
        }
    }
}
