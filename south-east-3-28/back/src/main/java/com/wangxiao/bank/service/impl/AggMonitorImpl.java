package com.wangxiao.bank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxiao.bank.entity.AggregateMonitoring;
import com.wangxiao.bank.entity.Card;
import com.wangxiao.bank.mapper.AggregateMonitoringMapper;
import com.wangxiao.bank.service.IAggMonitorservice;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Service
public class AggMonitorImpl extends ServiceImpl<AggregateMonitoringMapper, AggregateMonitoring> implements IAggMonitorservice {
    @Autowired
    private AggregateMonitoringMapper aggregateMonitoringMapper;
    public List<Card> getTypeCardData(String time) {
        List<Card>  cardList =aggregateMonitoringMapper.queryAll(time);
//        System.out.println("size:"+cardList);
        return cardList;
    }

}
