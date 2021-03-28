package com.wangxiao.bank.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangxiao.bank.entity.AggregateMonitoring;
import com.wangxiao.bank.entity.Card;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AggregateMonitoringMapper extends BaseMapper<AggregateMonitoring> {
    @Select("<script>"+
            "select "+
            "transaction_type as name"+",transaction_volume as count "+
            "FROM aggregate_monitoring WHERE agg_time=#{AggTime} GROUP BY name,count"+
            "</script>"
    )
    public List<Card> queryAll(@Param("AggTime")String AggTime);
}
