package com.vvvv.sevanUp.model.quartz;

import lombok.Data;

@Data
public class JobDetail extends JobDetailKey {
    private String description;

    private String jobClassName;

    private String isDurable;

    private String isNonconcurrent;

    private String isUpdateData;

    private String requestsRecovery;

    private byte[] jobData;
}