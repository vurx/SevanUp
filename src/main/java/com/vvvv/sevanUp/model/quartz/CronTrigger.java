package com.vvvv.sevanUp.model.quartz;

import lombok.Data;

@Data
public class CronTrigger extends CronTriggerKey {
    private String cronExpression;

    private String timeZoneId;

}