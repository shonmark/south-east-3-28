package com.wangxiao.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxiao.bank.entity.AggregateMonitoring;
import com.wangxiao.bank.entity.Card;

import java.util.List;

public interface IAggMonitorservice extends IService<AggregateMonitoring> {
    List<Card> getTypeCardData(String time);
}
