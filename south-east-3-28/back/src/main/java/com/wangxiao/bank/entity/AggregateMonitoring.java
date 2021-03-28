package com.wangxiao.bank.entity;

import lombok.Data;

@Data
public class AggregateMonitoring {
    private String transactionType;
    private String aggTime;
    private String transactionVolume;
}
