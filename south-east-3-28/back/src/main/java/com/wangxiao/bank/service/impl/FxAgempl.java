package com.wangxiao.bank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxiao.bank.entity.AggregateBussinessA;
import com.wangxiao.bank.entity.AtmAmount;
import com.wangxiao.bank.entity.AtmProvince;
import com.wangxiao.bank.entity.FxAge;
import com.wangxiao.bank.mapper.AggregateBussinessAMapper;
import com.wangxiao.bank.mapper.AtmAmountMapper;
import com.wangxiao.bank.mapper.AtmProvinceMapper;
import com.wangxiao.bank.mapper.FxAgeMapper;
import com.wangxiao.bank.service.AtmAmountservice;
import com.wangxiao.bank.service.AtmProvinceservice;
import com.wangxiao.bank.service.FxAgeservice;
import com.wangxiao.bank.service.IAggBAService;

@org.springframework.stereotype.Service
public class FxAgempl extends ServiceImpl<FxAgeMapper, FxAge> implements FxAgeservice {
}
