package com.wangxiao.bank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxiao.bank.entity.AggregateBussinessA;
import com.wangxiao.bank.entity.Card;
import com.wangxiao.bank.entity.HourAmount;
import com.wangxiao.bank.entity.LineElement;
import com.wangxiao.bank.mapper.AggregateBussinessAMapper;
import com.wangxiao.bank.service.IAggBAService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Service
public class AggBAImpl extends ServiceImpl<AggregateBussinessAMapper, AggregateBussinessA> implements IAggBAService {
    @Autowired
    private AggregateBussinessAMapper aggMapper;
    public List<Card> getTypeCardData(String time) {
        List<Card>  cardList =aggMapper.queryAll(time);
//        System.out.println("size:"+cardList);
       return cardList;
    }

    @Override
    public List<AggregateBussinessA> queryATMamount() {
        List<AggregateBussinessA> aggBussinessA = aggMapper.queryATMamounts();
        Collections.reverse(aggBussinessA);
        return aggBussinessA;
    }

    @Override
    public List<AggregateBussinessA> queryFxamount() {
        List<AggregateBussinessA> Fxamount = aggMapper.queryFxamount();
        Collections.reverse(Fxamount);
        return Fxamount;
    }

    public List<LineElement> getATMTime() {
        List<LineElement> atmTime =aggMapper.queryATMTime();
//        System.out.println("size:"+cardList);
        return atmTime;
    }

    @Override
    public List<LineElement> getFxTime() {
        List<LineElement> fxTime =aggMapper.queryFxTime();
        return fxTime;
    }

    @Override
    public List<Card> getTodayAmount(String time) {
        List<Card> todayAmount = aggMapper.queryTodayAmount(time);
        return todayAmount;
    }

    @Override
    public List<HourAmount> getHourAmount(String time) {
        List<HourAmount> hourAmount = aggMapper.queryHourAmount(time);
        return hourAmount;
    }


}
