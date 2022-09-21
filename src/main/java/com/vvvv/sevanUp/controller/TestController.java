package com.vvvv.sevanUp.controller;

import com.vvvv.sevanUp.basic.aspect.annotationAspect.DemoAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/aspect")
    @DemoAnnotation(no = 1, value = "测试方法", operation = "POST请求", isTrue = false)
    public Map<String, Object> testAspect(@RequestBody Map<String, Object> param) {
        log.info("正在执行control");
        return param;
    }
}
