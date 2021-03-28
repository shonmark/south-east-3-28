package com.wangxiao.bank.entity;

import lombok.Data;

@Data
public class AggregateBussinessA {
    private String transactionType;
    private String aggTime;
    private String kpiName;
    private String kpiValue;
}
