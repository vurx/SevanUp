package com.vvvv.sevanUp.controller;

import com.vvvv.sevanUp.service.ParkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ParkController {
    @Autowired
    private ParkService parkService;


    /**
     * 修改停车入场时间
     *
     * @param time 221100
     * @return
     */
    @GetMapping("/set")
    public String set(@RequestParam("time") String time) {
        parkService.set(time);
        return "ok";
    }

    /**
     * 手动触发停车通知
     *
     * @return
     */
    @GetMapping("/hand")
    public String hand() {
        parkService.send();
        return "ok";
    }

    // TODO
    @GetMapping("/stop")
    public String stop() {
        return "ok";
    }
}
