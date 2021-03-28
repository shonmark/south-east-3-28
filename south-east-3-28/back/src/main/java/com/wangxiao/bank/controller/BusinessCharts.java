package com.wangxiao.bank.controller;

import com.wangxiao.bank.entity.*;
import com.wangxiao.bank.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/business")
@CrossOrigin    // 消除csrf
public class BusinessCharts {

    @Autowired
    private IUserService service;

    @RequestMapping(value = "/line_charts_1",method = {RequestMethod.GET})
    public List<User> line_charts_1()
    {
        return service.list();

    }
    @Autowired
    private AtmAmountservice AtmAmountservices;

    @RequestMapping(value = "/line_charts_2",method = {RequestMethod.GET})
    public List<AtmAmount> line_charts_2()
    {
        return AtmAmountservices.list();
    }

    @Autowired
    private AtmProvinceservice AtmProvinceservices;


    @RequestMapping(value = "/line_charts_3",method = {RequestMethod.GET})
    public List<AtmProvince> line_charts_3()
    {
        return AtmProvinceservices.list();
    }

    @Autowired
    private FxAgeservice FxAgeservices;


    @RequestMapping(value = "/line_charts_4",method = {RequestMethod.GET})
    public List<FxAge> line_charts_4()
    {
        return FxAgeservices.list();
    }


    @Autowired
    private IFxAmService iFxAmService;

    @RequestMapping(value = "/line_charts_5",method = {RequestMethod.GET})
    public List<FxAmount> line_charts_5()
    {
        return iFxAmService.list();
    }

    @Autowired
    private IFxProService ifxpro;

    @RequestMapping(value = "/line_charts_6",method = {RequestMethod.GET})
    public List<FxProvince> line_charts_6()
    {
        return ifxpro.list();
    }

    @Autowired
    private IFxPurService iFxPurService;

    @RequestMapping(value = "/line_charts_7",method = {RequestMethod.GET})
    public List<FxPurpose> line_charts_7()
    {
        return iFxPurService.list();
    }

    @Autowired
    private IAggBAService iAggBAService;

    @RequestMapping(value = "/todayamount",method = {RequestMethod.GET})
    public List<Card> todayamount()
    {
        LocalDateTime nowTime= LocalDateTime.now().minusDays(1);
        String year = nowTime.getYear()+"";
        String month;
        if (nowTime.getMonthValue()<10) {
            month = "0" + nowTime.getMonthValue();
        }else {
            month = "" + nowTime.getMonthValue();
        }
        String day = nowTime.getDayOfMonth()+"";
        String hour;
        if (nowTime.getHour()<10) {
            hour = "0" + nowTime.getHour()+"";
        }else {
            hour = nowTime.getHour()+"";
        }

        String min;
        if (nowTime.getMinute()-1<10) {
            min = "0" + (nowTime.getMinute()-1);
        }else {
            min = (nowTime.getMinute()-1)+"";
        }
        String time = year+month+day+hour+min;
        return iAggBAService.getTodayAmount(time);
    }
}
