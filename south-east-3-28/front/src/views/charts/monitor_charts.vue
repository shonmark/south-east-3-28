<template>
  <div>
    <Header></Header>
    <div class="row" style="margin-right: 0px;margin-left: 0px;">
      <div class="col-3 col-md-2 bg-light" style="padding-right: 0px;padding-left: 0px;">
        <Sider current="1"></Sider>
      </div>
      <div class="col-9 col-md-10 bg-light south" style="padding-right: 0px;padding-left: 0px;">
        <!--        卡片-->
          <div class="shadow-lg row bg-light border p-3" style="margin: 20px;">
            <div class="card" style="width: 15rem;height: 9rem;margin: 20px 25px 20px 25px;">
              <div class="card-body middle">
  <!--              <h5 class="card-title"></h5>-->
                <h6 class="card-subtitle mb-2 text-muted">ATM实时交易量</h6>
                <h4 class="card-text">{{ card[0].count }}<small>笔</small></h4>
              </div>
            </div>
            <div class="card" style="width: 15rem;height: 9rem;margin: 20px 25px;">
              <div class="card-body middle">
                <!--            <h5 class="card-title">AMT实时交易量</h5>-->
                <h6 class="card-subtitle mb-2 text-muted">ATM实时相应时间</h6>
<!--                text-danger-->
                <h4 class="card-text" v-bind:class="{ show_orange:(card2[0].count/card[0].count>110)&(card2[0].count/card[0].count<150),
                show_red:card2[0].count/card[0].count>150 }">{{ (card2[0].count/card[0].count).toFixed(2) }}<small>ms</small></h4>
              </div>
            </div>
            <div class="card" style="width: 15rem;height: 9rem;margin: 20px 25px;">
              <div class="card-body middle">
                <!--            <h5 class="card-title">AMT实时交易量</h5>-->
                <h6 class="card-subtitle mb-2 text-muted">外汇买入实时交易量</h6>
                <h4 class="card-text">{{ card[1].count }}<small>笔</small></h4>
              </div>
            </div>
            <div class="card" style="width: 15rem;height: 9rem;margin: 20px 50px 20px 25px;">
              <div class="card-body middle">
                <!--            <h5 class="card-title">AMT实时交易量</h5>-->
                <h6 class="card-subtitle mb-2 text-muted">外汇买入实时相应时间</h6>
                <h4 class="card-text" v-bind:class="{ show_orange:(card2[1].count/card[1].count>120)&(card2[1].count/card[1].count<180),show_red:card2[1].count/card[1].count>180 }">{{ (card2[1].count/card[1].count).toFixed(2) }}<small>ms</small></h4>
              </div>
            </div>
          </div>
  <!--        图表-->
          <div class="shadow-lg row bg-light border p-3" style="margin: 20px;">
            <div id="line_charts_1" style="width: 580px;height:400px;"></div>
            <div id="line_charts_2" style="width: 580px;height:400px;"></div>
            <div id="line_charts_3" style="width: 580px;height:400px;"></div>
            <div id="line_charts_4" style="width: 580px;height:400px;"></div>
          </div>

      </div>
    </div>
  </div>
</template>



<script>
import * as echarts from 'echarts';

import Header from "@/components/Header";
import Sider from "@/components/Sider";
import axios from "axios";
export default {
  name: "Charts",
  components: {Sider, Header},
  mounted() {
    // this.drawPie();
    // this.drawBar();
    setInterval(function (){

    },6000)
    axios
        .get('http://localhost/monitor/card')
        .then(response => (this.card = response.data))
    axios
        .get('http://localhost/monitor/card2')
        .then(response => (
            this.card2 = response.data
        ))

    axios
        .get('http://localhost/monitor/line_charts_1')
        .then(response => {
          this.line_charts_1_data = response.data
          this.drawLine1()
        })
    axios
        .get('http://localhost/monitor/line_charts_2')
        .then(response => {
          this.line_charts_2_data = response.data
          this.drawLine2()
        })
    axios
        .get('http://localhost/monitor/line_charts_3')
        .then(response => {
          this.line_charts_3_data = response.data
          this.drawLine3()
        })
    axios
        .get('http://localhost/monitor/line_charts_4')
        .then(response => {
          this.line_charts_4_data = response.data
          this.drawLine4()
        })

  },
  data(){
    return {
      line_charts_1_data:[
          {"transactionType":"ATM001","aggTime":"","kpiName":"volume","kpiValue":""},
        {"transactionType":"ATM001","aggTime":"","kpiName":"volume","kpiValue":""},
        {"transactionType":"ATM001","aggTime":"","kpiName":"volume","kpiValue":""},
        {"transactionType":"ATM001","aggTime":"","kpiName":"volume","kpiValue":""},
        {"transactionType":"ATM001","aggTime":"","kpiName":"volume","kpiValue":""},
        {"transactionType":"ATM001","aggTime":"","kpiName":"volume","kpiValue":""},
        {"transactionType":"ATM001","aggTime":"","kpiName":"volume","kpiValue":""}
        ],
      line_charts_2_data:[
        {"transactionType":"ATM001","aggTime":"","kpiName":"volume","kpiValue":""},
        {"transactionType":"ATM001","aggTime":"","kpiName":"volume","kpiValue":""},
        {"transactionType":"ATM001","aggTime":"","kpiName":"volume","kpiValue":""},
        {"transactionType":"ATM001","aggTime":"","kpiName":"volume","kpiValue":""},
        {"transactionType":"ATM001","aggTime":"","kpiName":"volume","kpiValue":""},
        {"transactionType":"ATM001","aggTime":"","kpiName":"volume","kpiValue":""},
        {"transactionType":"ATM001","aggTime":"","kpiName":"volume","kpiValue":""}
      ],
      line_charts_3_data:[
        {"aggtime":"","time":""},
        {"aggtime":"","time":""},
        {"aggtime":"","time":""},
        {"aggtime":"","time":""},
        {"aggtime":"","time":""},
        {"aggtime":"","time":""},
        {"aggtime":"","time":""},
      ],
      line_charts_4_data:[
        {"aggtime":"","time":""},
        {"aggtime":"","time":""},
        {"aggtime":"","time":""},
        {"aggtime":"","time":""},
        {"aggtime":"","time":""},
        {"aggtime":"","time":""},
        {"aggtime":"","time":""},
      ],
      card:[{
        "name":"ATM001",
        "count":"1",
      },
      {
        "name":"FX001",
        "count":"1",
      }
      ],
      card2:[{
        "name":"ATM001",
        "count":"0",
      },
        {
          "name":"FX001",
          "count":"0",
        }
      ],

    }
  },
  methods:{
    drawLine1(){
      // 定位图表位置
      let line_charts = echarts.init(document.getElementById('line_charts_1'));
      let option = {
        title: {
          text: 'ATM交易金额',
          // subtext: '纯属虚构'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: [
            this.line_charts_1_data[2].aggTime,
            this.line_charts_1_data[3].aggTime,
            this.line_charts_1_data[4].aggTime,
            this.line_charts_1_data[5].aggTime,
            this.line_charts_1_data[6].aggTime,

          ]
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [
            this.line_charts_1_data[2].kpiValue,
            this.line_charts_1_data[3].kpiValue,
            this.line_charts_1_data[4].kpiValue,
            this.line_charts_1_data[5].kpiValue,
            this.line_charts_1_data[6].kpiValue,

          ],
          type: 'line'
        }]
      };
      line_charts.setOption(option)
      //建议加上以下这一行代码，不加的效果图如下（当浏览器窗口缩小的时候）。超过了div的界限（红色边框）
      //window.addEventListener('resize',function() {line_charts.resize()});
    },

    drawLine2(){
      // 定位图表位置
      let line_charts = echarts.init(document.getElementById('line_charts_2'));
      let option = {
        title: {
          text: '外汇交易金额',
          // subtext: '纯属虚构'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: [
            this.line_charts_2_data[2].aggTime,
            this.line_charts_2_data[3].aggTime,
            this.line_charts_2_data[4].aggTime,
            this.line_charts_2_data[5].aggTime,
            this.line_charts_2_data[6].aggTime,

          ]
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [
            this.line_charts_2_data[2].kpiValue,
            this.line_charts_2_data[3].kpiValue,
            this.line_charts_2_data[4].kpiValue,
            this.line_charts_2_data[5].kpiValue,
            this.line_charts_2_data[6].kpiValue,

          ],
          type: 'line'
        }]
      };
      line_charts.setOption(option)
      //建议加上以下这一行代码，不加的效果图如下（当浏览器窗口缩小的时候）。超过了div的界限（红色边框）
      //window.addEventListener('resize',function() {line_charts.resize()});
    },

    drawLine3(){
      // 定位图表位置
      let line_charts = echarts.init(document.getElementById('line_charts_3'));
      let option = {
        title: {
          text: 'ATM响应时间',
          // subtext: '纯属虚构'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: [
            this.line_charts_3_data[4].time,
            this.line_charts_3_data[3].time,
            this.line_charts_3_data[2].time,
            this.line_charts_3_data[1].time,
            this.line_charts_3_data[0].time,
          ]
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [
            this.line_charts_3_data[4].aggtime,
            this.line_charts_3_data[3].aggtime,
            this.line_charts_3_data[2].aggtime,
            this.line_charts_3_data[1].aggtime,
            this.line_charts_3_data[0].aggtime,

          ],
          type: 'line'
        }]
      };
      line_charts.setOption(option)
      //建议加上以下这一行代码，不加的效果图如下（当浏览器窗口缩小的时候）。超过了div的界限（红色边框）
      //window.addEventListener('resize',function() {line_charts.resize()});
    },

    drawLine4(){
      // 定位图表位置
      let line_charts = echarts.init(document.getElementById('line_charts_4'));
      let option = {
        title: {
          text: '外汇响应时间',
          // subtext: '纯属虚构'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: [
            this.line_charts_4_data[4].time,
            this.line_charts_4_data[3].time,
            this.line_charts_4_data[2].time,
            this.line_charts_4_data[1].time,
            this.line_charts_4_data[0].time,
          ]
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [
            this.line_charts_4_data[4].aggtime,
            this.line_charts_4_data[3].aggtime,
            this.line_charts_4_data[2].aggtime,
            this.line_charts_4_data[1].aggtime,
            this.line_charts_4_data[0].aggtime,

          ],
          type: 'line'
        }]
      };
      line_charts.setOption(option)
      //建议加上以下这一行代码，不加的效果图如下（当浏览器窗口缩小的时候）。超过了div的界限（红色边框）
      //window.addEventListener('resize',function() {line_charts.resize()});
    },

    //pie_charts
    // drawPie(){
    //   let pie_charts = document.getElementById('pie_charts');
    //   let pieCharts = echarts.init(pie_charts);
    //   let option = {
    //     tooltip: {
    //       trigger: 'item'
    //     },
    //     legend: {
    //       top: '5%',
    //       left: 'center'
    //     },
    //     series: [
    //       {
    //         name: '访问来源',
    //         type: 'pie',
    //         radius: ['40%', '70%'],
    //         avoidLabelOverlap: false,
    //         label: {
    //           show: false,
    //           position: 'center'
    //         },
    //         emphasis: {
    //           label: {
    //             show: true,
    //             fontSize: '40',
    //             fontWeight: 'bold'
    //           }
    //         },
    //         labelLine: {
    //           show: false
    //         },
    //         data: [
    //           {value: 1048, name: '搜索引擎'},
    //           {value: 735, name: '直接访问'},
    //           {value: 580, name: '邮件营销'},
    //           {value: 484, name: '联盟广告'},
    //           {value: 300, name: '视频广告'}
    //         ]
    //       }
    //     ]
    //   };
    //
    //   option && pieCharts.setOption(option);
    // },

    //柱状图bar
    // drawBar(){
    //   let app = {};
    //
    //   let chartDom = document.getElementById('bar_charts');
    //   let myChart = echarts.init(chartDom);
    //   let option;
    //
    //   option = {
    //     title: {
    //       text: '动态数据',
    //       subtext: '纯属虚构'
    //     },
    //     tooltip: {
    //       trigger: 'axis',
    //       axisPointer: {
    //         type: 'cross',
    //         label: {
    //           backgroundColor: '#283b56'
    //         }
    //       }
    //     },
    //     legend: {
    //       data:['最新成交价', '预购队列']
    //     },
    //     toolbox: {
    //       show: true,
    //       feature: {
    //         dataView: {readOnly: false},
    //         restore: {},
    //         saveAsImage: {}
    //       }
    //     },
    //     dataZoom: {
    //       show: false,
    //       start: 0,
    //       end: 100
    //     },
    //     xAxis: [
    //       {
    //         type: 'category',
    //         boundaryGap: true,
    //         data: (function (){
    //           let now = new Date();
    //           let res = [];
    //           let len = 10;
    //           while (len--) {
    //             res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
    //             now = new Date(now - 2000);
    //           }
    //           return res;
    //         })()
    //       },
    //       {
    //         type: 'category',
    //         boundaryGap: true,
    //         data: (function (){
    //           let res = [];
    //           let len = 10;
    //           while (len--) {
    //             res.push(10 - len - 1);
    //           }
    //           return res;
    //         })()
    //       }
    //     ],
    //     yAxis: [
    //       {
    //         type: 'value',
    //         scale: true,
    //         name: '价格',
    //         max: 30,
    //         min: 0,
    //         boundaryGap: [0.2, 0.2]
    //       },
    //       {
    //         type: 'value',
    //         scale: true,
    //         name: '预购量',
    //         max: 1200,
    //         min: 0,
    //         boundaryGap: [0.2, 0.2]
    //       }
    //     ],
    //     series: [
    //       {
    //         name: '预购队列',
    //         type: 'bar',
    //         xAxisIndex: 1,
    //         yAxisIndex: 1,
    //         data: (function (){
    //           let res = [];
    //           let len = 10;
    //           while (len--) {
    //             res.push(Math.round(Math.random() * 1000));
    //           }
    //           return res;
    //         })()
    //       },
    //       {
    //         name: '最新成交价',
    //         type: 'line',
    //         data: (function (){
    //           let res = [];
    //           let len = 0;
    //           while (len < 10) {
    //             res.push((Math.random()*10 + 5).toFixed(1) - 0);
    //             len++;
    //           }
    //           return res;
    //         })()
    //       }
    //     ]
    //   };
    //
    //   app.count = 11;
    //   setInterval(function (){
    //     let axisData = (new Date()).toLocaleTimeString().replace(/^\D*/, '');
    //
    //     let data0 = option.series[0].data;
    //     let data1 = option.series[1].data;
    //     data0.shift();
    //     data0.push(Math.round(Math.random() * 1000));
    //     data1.shift();
    //     data1.push((Math.random() * 10 + 5).toFixed(1) - 0);
    //
    //     option.xAxis[0].data.shift();
    //     option.xAxis[0].data.push(axisData);
    //     option.xAxis[1].data.shift();
    //     option.xAxis[1].data.push(app.count++);
    //
    //     myChart.setOption(option);
    //   }, 2100);
    //
    //   option && myChart.setOption(option);
    // },

  }
}
</script>

<style scoped>
.south{
  margin-top: 60px
}
.show_orange{
  color: orange;
}
.show_red{
  color: red;
}
.middle {
  padding-top: 50px
}
</style>