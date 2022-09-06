package com.vvvv.sevanUp.model.quartz;

import lombok.Data;

@Data
public class CronTriggerKey {
    private String schedName;

    private String triggerName;

    private String triggerGroup;
}