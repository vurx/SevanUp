package com.vvvv.sevanUp.basic.aspect.annotationAspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Aspect
@Slf4j
@Order(1) //当存在多个拦截器，该注解指定执行的优先级（越小越高）
public class DemoAspect {

    @Pointcut("@annotation(annotation) && args(param)")
    public void ePointCut(DemoAnnotation annotation, Map<String,Object> param) {
    }

    @Around("ePointCut(annotation,param)")
    public Object doAround(ProceedingJoinPoint point, DemoAnnotation annotation, Map<String, Object> param) throws Throwable {
        log.info("----获取自定义注解参数----");
        log.info("no:{},value:{},operation:{},bool:{}", annotation.no(), annotation.value(), annotation.operation(), annotation.isTrue());
        log.info("----获取目标方法注入参数----");
        log.info(JSON.toJSONString(param));
        log.info("----aop:目标方法执行前----");
        Object proceed = point.proceed();
        log.info("----aop:目标方法执行后----");
        return proceed;
    }
}
