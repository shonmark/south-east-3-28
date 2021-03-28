package com.wangxiao.bank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxiao.bank.entity.AggregateBussinessA;
import com.wangxiao.bank.entity.AtmAmount;
import com.wangxiao.bank.mapper.AggregateBussinessAMapper;
import com.wangxiao.bank.mapper.AtmAmountMapper;
import com.wangxiao.bank.service.AtmAmountservice;
import com.wangxiao.bank.service.IAggBAService;

@org.springframework.stereotype.Service
public class AtmAmountmpl extends ServiceImpl<AtmAmountMapper, AtmAmount> implements AtmAmountservice {
}
