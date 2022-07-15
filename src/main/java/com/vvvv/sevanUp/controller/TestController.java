package com.vvvv.sevanUp.controller;

import com.vvvv.sevanUp.instance.park.Parking;
import com.vvvv.sevanUp.mapper.excel.SubsinstSynTempMapper;
import com.vvvv.sevanUp.model.excel.SubsinstSynTemp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private SubsinstSynTempMapper synTempMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Parking parking;

    @GetMapping("/test")
    public String test(){
        SubsinstSynTemp subsinstSynTemp = new SubsinstSynTemp();
        subsinstSynTemp
                .setId(1)
                .setSystemid("123")
                .setReginid("123d")
                .setProdspeccode("123")
                .setUseridtype("123")
                .setProductno("123")
                .setVproductid("123")
                .setSpid("123")
                .setProductoffertype("123")
                .setProdofferid("123")
                .setStatus("123")
                .setSubscribetime("123")
                .setEffdate("123")
                .setExpdate("123")
                .setChannelplayerid("123")
                .setDealFlag(1)
                .setOutParam("123")
                .setCreateData(new Date())
        ;
        synTempMapper.insert(subsinstSynTemp);
        return "傻逼";
    }

    @GetMapping("/set")
    public String test2(@RequestParam("time") String time) {
        redisTemplate.opsForValue().set("parkStr",time);
        log.info("停车时间更新成功：{}", time);
        parking.initParkInfo();
        return "ok";
    }


}
