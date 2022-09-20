package com.vvvv.sevanUp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class KfkController {

    @Autowired
    private KafkaTemplate<String,String> kfk;

    @RequestMapping("query")
    public String QueryAccount(@RequestParam (defaultValue = "test") String channel) {
        kfk.send("mac", channel);
        return "ok";
    }
}
