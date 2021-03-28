package com.wangxiao.bank.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangxiao.bank.entity.AggregateBussinessA;
import com.wangxiao.bank.entity.Card;
import com.wangxiao.bank.entity.HourAmount;
import com.wangxiao.bank.entity.LineElement;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AggregateBussinessAMapper extends BaseMapper<AggregateBussinessA> {

    @Select("<script>"+
            "select "+
            "transaction_type as name"+",kpi_value as count "+
            "FROM aggregate_bussiness_a "+
            "WHERE kpi_name = 'count' and agg_time=#{AggTime} GROUP BY name,count"+
            "</script>"
    )
    public List<Card>  queryAll(@Param("AggTime")String AggTime);

    @Select("<script>"+
            "select transaction_type,concat(SUBSTRING(aggregate_bussiness_a.agg_time,9,2),\":\",SUBSTRING(aggregate_bussiness_a.agg_time,11,2)) as aggTime,kpi_name,kpi_value "+
            "FROM aggregate_bussiness_a "+"WHERE transaction_type=\"ATM001\" "+
            "AND kpi_name=\"volume\"  order by agg_time desc limit 7 "+
            "</script>"
    )
    public List<AggregateBussinessA>  queryATMamounts();

    @Select("<script>"+
            "select transaction_type,concat(SUBSTRING(aggregate_bussiness_a.agg_time,9,2),\":\",SUBSTRING(aggregate_bussiness_a.agg_time,11,2)) as aggTime,kpi_name,kpi_value "+
            "FROM aggregate_bussiness_a "+"WHERE transaction_type=\"FX001\" "+
            "AND kpi_name=\"volume\"  order by agg_time desc limit 7 "+
            "</script>"
    )
    public List<AggregateBussinessA>  queryFxamount();

    @Select("<script>"+
            "SELECT FORMAT(aggregate_monitoring.transaction_volume/aggregate_bussiness_a.kpi_value,2) AS aggtime,"+
            "concat(SUBSTRING(aggregate_bussiness_a.agg_time,9,2),\":\", SUBSTRING(aggregate_bussiness_a.agg_time,11,2)) AS time "+
            "FROM aggregate_bussiness_a INNER JOIN aggregate_monitoring "+
            "ON aggregate_bussiness_a.agg_time = aggregate_monitoring.agg_time "+
            "AND aggregate_bussiness_a.transaction_type = aggregate_monitoring.transaction_type "+
            "WHERE aggregate_bussiness_a.kpi_name='count' AND aggregate_bussiness_a.transaction_type = \"ATM001\" "+
            "ORDER BY aggregate_bussiness_a.agg_time DESC LIMIT 7"+
            "</script>"
    )
    public List<LineElement>  queryATMTime();

    @Select("<script>"+
            "SELECT FORMAT(aggregate_monitoring.transaction_volume/aggregate_bussiness_a.kpi_value,2) AS aggtime,"+
            "concat(SUBSTRING(aggregate_bussiness_a.agg_time,9,2),\":\", SUBSTRING(aggregate_bussiness_a.agg_time,11,2)) AS time "+
            "FROM aggregate_bussiness_a INNER JOIN aggregate_monitoring "+
            "ON aggregate_bussiness_a.agg_time = aggregate_monitoring.agg_time "+
            "AND aggregate_bussiness_a.transaction_type = aggregate_monitoring.transaction_type "+
            "WHERE aggregate_bussiness_a.kpi_name='count' AND aggregate_bussiness_a.transaction_type = \"FX001\" "+
            "ORDER BY aggregate_bussiness_a.agg_time DESC LIMIT 7"+
            "</script>"
    )
    public List<LineElement> queryFxTime();

    @Select("<script>"+
            "SELECT transaction_type as name,SUM(CAST(kpi_value AS signed)) as count "+
            "FROM aggregate_bussiness_a WHERE kpi_name = \"volume\" "+
            "AND agg_time>#{AggTime} GROUP BY transaction_type;"+
            "</script>"
    )
    public List<Card> queryTodayAmount(@Param("AggTime")String AggTime);

    @Select("<script>"+
            "SELECT transaction_type as name,SUM(CAST(kpi_value AS signed)) as count,"+
            "concat(SUBSTRING(aggregate_bussiness_a.agg_time,9,2),\":\", SUBSTRING(aggregate_bussiness_a.agg_time,11,2)) AS time "+
            "FROM aggregate_bussiness_a WHERE kpi_name = \"volume\" "+
            "AND agg_time>#{AggTime} GROUP BY name,time;"+
            "</script>"
    )
    public List<HourAmount> queryHourAmount(@Param("AggTime")String AggTime);

}
