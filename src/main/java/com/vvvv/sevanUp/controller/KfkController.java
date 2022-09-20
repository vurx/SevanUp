package com.vvvv.sevanUp.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/kafka")
public class KfkController {

    @Autowired
    private KafkaTemplate<String, String> kfk;

    @PostMapping("/send")
    public HashMap<String, String> QueryAccount(@RequestBody HashMap<String, String> param) {
        kfk.send("topic-tumbling", JSON.toJSONString(param));
        kfk.send("topic-hopping", JSON.toJSONString(param));
        return param;
    }
}
