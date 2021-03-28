package com.wangxiao.bank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wangxiao.bank.entity.FxAmount;

import com.wangxiao.bank.mapper.FxAmountMapper;

import com.wangxiao.bank.service.IFxAmService;

@org.springframework.stereotype.Service
public class FxAmImpl extends ServiceImpl<FxAmountMapper,FxAmount> implements IFxAmService {
}
