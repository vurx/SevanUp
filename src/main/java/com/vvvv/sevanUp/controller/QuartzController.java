package com.vvvv.sevanUp.controller;

import com.github.pagehelper.PageInfo;
import com.vvvv.sevanUp.model.quartz.JobAndTriggerDto;
import com.vvvv.sevanUp.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping(path = "/quartz")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    /**
     * 新增定时任务
     *
     * @param name 任务名称
     * @param group 任务组
     * @param cron cron表达式
     * @return ResultMap
     */
    @PostMapping(path = "/addjob")
    @ResponseBody
    public String addjob(String name, String group, String cron) {
        String jName = name + "_job";
        String tName = name + "_trigger";
        quartzService.addjob(jName, group, tName, group, cron);
        return "添加任务成功";
    }

    /**
     * 暂停任务
     *
     * @param jName 任务名称
     * @param jGroup 任务组
     * @return ResultMap
     */
    @PostMapping(path = "/pausejob")
    @ResponseBody
    public String pausejob(String jName, String jGroup) {
        try {
            quartzService.pausejob(jName, jGroup);
            return "暂停任务成功";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "暂停任务失败";
        }
    }

    /**
     * 恢复任务
     *
     * @param jName 任务名称
     * @param jGroup 任务组
     * @return ResultMap
     */
    @PostMapping(path = "/resumejob")
    @ResponseBody
    public String resumejob(String jName, String jGroup) {
        try {
            quartzService.resumejob(jName, jGroup);
            return "恢复任务成功";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "恢复任务失败";
        }
    }

    /**
     * 重启任务
     *
     * @param jName 任务名称
     * @param jGroup 任务组
     * @param cron cron表达式
     * @return ResultMap
     */
    @PostMapping(path = "/reschedulejob")
    @ResponseBody
    public String rescheduleJob(String jName, String jGroup, String cron) {
        try {
            quartzService.rescheduleJob(jName, jGroup, cron);
            return "重启任务成功";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "重启任务失败";
        }
    }

    /**
     * 删除任务
     *
     * @param jName 任务名称
     * @param jGroup 任务组
     * @return ResultMap
     */
    @PostMapping(path = "/deletejob")
    @ResponseBody
    public String deletejob(String jName, String jGroup) {
        try {
            quartzService.deletejob(jName, jGroup);
            return "删除任务成功";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "删除任务失败";
        }
    }

    /**
     * 查询任务
     *
     * @param pageNum 页码
     * @param pageSize 每页显示多少条数据
     * @return Map
     */
    @GetMapping(path = "/queryjob")
    @ResponseBody
    public Map<String, Object> queryjob(Integer pageNum, Integer pageSize) {
        PageInfo<JobAndTriggerDto> pageInfo = quartzService.getJobAndTriggerDetails(pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(pageInfo.getTotal())) {
            map.put("JobAndTrigger", pageInfo);
            map.put("number", pageInfo.getTotal());
        }
        return map;
    }
}
