package com.wangxiao.bank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxiao.bank.service.IUserService;
import com.wangxiao.bank.entity.User;
import com.wangxiao.bank.mapper.UserMapper;


@org.springframework.stereotype.Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
