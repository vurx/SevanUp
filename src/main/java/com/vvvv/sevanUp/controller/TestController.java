package com.vvvv.sevanUp.controller;

import com.vvvv.sevanUp.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test(){
//        testService.dosome();
        return "傻逼";
    }
}
