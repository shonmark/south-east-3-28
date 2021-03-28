package com.wangxiao.bank.controller;

import com.wangxiao.bank.entity.Sider;
import com.wangxiao.bank.service.ISiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/url")
@CrossOrigin    // 消除csrf
public class UrlController {
    @Autowired
    private ISiderService iSiderService;

    @RequestMapping(value = "/sider",method = {RequestMethod.GET})
    public List<Sider> sider()
    {
        return iSiderService.list();
    }
}
