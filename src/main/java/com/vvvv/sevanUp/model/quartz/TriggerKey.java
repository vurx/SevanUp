package com.vvvv.sevanUp.model.quartz;

import lombok.Data;

@Data
public class TriggerKey {
    private String schedName;

    private String triggerName;

    private String triggerGroup;

}