package com.vvvv.sevanUp.model.quartz;

import lombok.Data;

@Data
public class JobDetailKey {
    private String schedName;

    private String jobName;

    private String jobGroup;
}