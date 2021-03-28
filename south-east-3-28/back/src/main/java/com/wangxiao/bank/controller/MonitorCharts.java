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
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/monitor")
@CrossOrigin    // 消除csrf
public class MonitorCharts {

    @Autowired
    private IAggBAService iAggBAService;

    @RequestMapping(value = "/card",method = {RequestMethod.GET})
    public List<Card> card()
    {
        LocalDateTime nowTime= LocalDateTime.now();
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

        System.out.println("---------------nowTime----------------");
        System.out.println(time);
        return iAggBAService.getTypeCardData(time);
    }

    @Autowired
    private IAggMonitorservice iAggMonitorservice;

    @RequestMapping(value = "/card2",method = {RequestMethod.GET})
    public List<Card> card2()
    {
        LocalDateTime nowTime= LocalDateTime.now();
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
        return iAggMonitorservice.getTypeCardData(time);
    }

    @RequestMapping(value = "/line_charts_1",method = {RequestMethod.GET})
    public List<AggregateBussinessA> line_charts_1()
    {
        return iAggBAService.queryATMamount();
    }

    @RequestMapping(value = "/line_charts_2",method = {RequestMethod.GET})
    public List<AggregateBussinessA> line_charts_2()
    {
        return iAggBAService.queryFxamount();
    }

    @RequestMapping(value = "/line_charts_3",method = {RequestMethod.GET})
    public List<LineElement> line_charts_3()
    {
        return iAggBAService.getATMTime();
    }

    @RequestMapping(value = "/line_charts_4",method = {RequestMethod.GET})
    public List<LineElement> line_charts_4()
    {
        return iAggBAService.getFxTime();
    }


    @RequestMapping(value = "/hour_amount",method = {RequestMethod.GET})
    public List<HourAmount> hour_amount()
    {
        LocalDateTime beforeTime= LocalDateTime.now().minusMinutes(71);
        String year = beforeTime.getYear()+"";
        String month;
        if (beforeTime.getMonthValue()<10) {
            month = "0" + beforeTime.getMonthValue();
        }else {
            month = "" + beforeTime.getMonthValue();
        }
        String day = beforeTime.getDayOfMonth()+"";
        String hour;
        if (beforeTime.getHour()<10) {
            hour = "0" + beforeTime.getHour();
        }else {
            hour = beforeTime.getHour()+"";
        }

        String min;
        if (beforeTime.getMinute()<10) {
            min = "0" + beforeTime.getMinute();
        }else {
            min = beforeTime.getMinute()+"";
        }
//        String time = year+month+day+hour+min;

        String time = year+month+day+hour+min;
        List<HourAmount> hourAmount = iAggBAService.getHourAmount(time);
        if (hourAmount.size()>140){
            for(int i = 0; i<=hourAmount.size()-140;i++){
                hourAmount.remove(hourAmount.size()-1);
//                System.out.println(hourAmount.size()-1);
            }
        }
        return hourAmount;
    }

}
