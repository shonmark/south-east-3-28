package com.wangxiao.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxiao.bank.entity.AggregateBussinessA;
import com.wangxiao.bank.entity.Card;
import com.wangxiao.bank.entity.HourAmount;
import com.wangxiao.bank.entity.LineElement;
import com.wangxiao.bank.mapper.AggregateBussinessAMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface IAggBAService extends IService<AggregateBussinessA> {
    List<Card> getTypeCardData(String time);
    List<AggregateBussinessA> queryATMamount();

    List<AggregateBussinessA> queryFxamount();
    List<LineElement> getATMTime();
    List<LineElement> getFxTime();
    List<Card> getTodayAmount(String time);
    List<HourAmount> getHourAmount(String time);
}

