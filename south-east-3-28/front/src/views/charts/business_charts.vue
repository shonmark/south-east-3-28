<template xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
  <div>
    <Header></Header>
    <div class="row" style="margin-right: 0px;margin-left: 0px;">
      <div class="col-sm-3 col-md-2 bg-light" style="padding-right:0px; padding-left:0px;">
        <Sider current="2"></Sider>
      </div>
      <div class="col-sm-9 col-md-10 bg-light south" style="padding-right: 0px;padding-left: 0px;">
        <!--        图表-->
        <div class="shadow-lg row bg-light border p-3" style="margin: 20px;">
          <div class="col-sm-9 col-md-9">
<!--            左侧图表-->
            <h5>实时交易情况</h5>
            <hr size="5px" noshade=true />
            <div class="row">
              <div style="width: 200px;height:100px;margin-right: 235px">
                <h5 class="text-muted" style="float: left">
                  今日ATM交易金额
                </h5><br/>
                <h3 style="float: left">
                  {{today_amount[0].count}}元
                </h3>
              </div>
              <div style="width: 200px;height:100px;">
                <h5 class="text-muted" style="float: left">
                  今日外汇交易金额
                </h5><br/>
                <h3 style="float: left">
                  {{today_amount[1].count}}元
                </h3>
              </div>
              <div id="line_charts_1" style="width: 800px;height:400px;"></div>
              <div id="line_charts_2" style="width: 800px;height:400px;"></div>
            </div>
<!--            <div id="china_map" style="width: 600px;height:400px;"></div>-->
            <h5>外汇目的比例</h5>
            <hr size="5px" noshade=true />
            <div class="row">
              <div id="percent_charts_2" style="width: 175px;height:175px;"></div>
              <div id="percent_charts_3" style="width: 175px;height:175px;"></div>
              <div id="percent_charts_4" style="width: 175px;height:175px;"></div>
              <div id="percent_charts_5" style="width: 175px;height:175px;"></div>
              <div id="percent_charts_1" style="width: 175px;height:175px;"></div>
            </div>
          </div>
          <div class="col-sm-3 col-md-3">
            <h5>各交易类型交易量占比</h5>
            <hr size="5px" noshade=true />
            <div>
<!--              右侧图表-->
              <div id="pie_charts_1" style="width: 300px;height:250px;"></div>
              <div id="pie_charts_2" style="width: 300px;height:250px;"></div>
              <div id="pie_charts_3" style="width: 300px;height:250px;"></div>
            </div>
          </div>
        </div>

<!--        <div id="bar_charts"></div>-->
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
  name: "Business",
  components: {Sider, Header},
  data(){
    return {
      today_amount:[
        {
        "name":"",
        "count":"",
        },
        {
          "name":"",
          "count":"",
        }
      ],
      hour_amount:[],
      percent_charts_data:[],
      line_charts_5_data:[],
      percent_charts_data_all : [],
      percent_charts_1_data : [],
      percent_charts_2_data : [],
      percent_charts_3_data : [],
      percent_charts_4_data : [],
      percent_charts_5_data : [],
      line_charts_3_data:[],
      line_charts_2_data2 :[],
      line_charts_3_data2:[],
      fx_hour_amount:[{
        "value":"",
        "name":"",
      }],
      atm_hour_amount:[{
        "value":"",
        "name":"",
      }],
      fill : { gradient: ["red", "green", "blue"] },
      line_charts_2_data : [],
    }
  },
  mounted() {

    // this.draw_china_map();
    // setInterval(function (){
    //
    //     },1000);
    axios
        .get('http://localhost/business/line_charts_2')
        .then(response => {
          this.line_charts_2_data = response.data
          // console.log(this.line_charts_2_data)
          for(let i=0;i<this.line_charts_2_data.length;i++){
            this.line_charts_2_data2.push({
              value:this.line_charts_2_data[i].amount,
              name:this.line_charts_2_data[i].percent,
            })
          }
          this.draw_pie_charts_1();
        }).catch(
            error => console.log(error)
        )
    axios
        .get('http://localhost/business/todayamount')
        .then(response => {
          this.today_amount = response.data

        }).catch(
        error => console.log(error)
    )
    axios
        .get('http://localhost/monitor/hour_amount')
        .then(response => {
          this.hour_amount = response.data

          for(let i=0;i<7;i++){
            let atm_value = 0
            let atm_name = this.hour_amount[20*i].time
            let fx_value = 0
            let fx_name = this.hour_amount[i*20+1].time
            let atm_last_name
            for (let j=0;j<10;j++){

              atm_value += this.hour_amount[20*i+j*2].count
              fx_value += this.hour_amount[i*20+1+j*2].count
              // atm_last_name = this.hour_amount[20*i+j*2].time
            }
            this.atm_hour_amount.push({
              value:atm_value/10000,
              // name:atm_name+"-"+this.hour_amount[20*i+9].time,
              name:atm_name+"-"+this.hour_amount[20*i+18].time,
            })

            this.fx_hour_amount.push({
              value:fx_value/10000,
              // name:fx_name+"-"+this.hour_amount[20*i+10].time,
              name:fx_name+"-"+this.hour_amount[20*i+18].time,
            })

          }
          // console.log(this.atm_hour_amount)
          this.draw_line_charts_1();
          this.draw_line_charts_2();

        }).catch(
        error => console.log(error)
    )
    axios
        .get('http://localhost/business/line_charts_7')
        .then(response => {
          this.percent_charts_data = response.data
          // console.log(this.percent_charts_data)
          this.percent_charts_1_data = this.percent_charts_data[0]
          this.percent_charts_2_data = this.percent_charts_data[1]
          this.percent_charts_3_data = this.percent_charts_data[2]
          this.percent_charts_4_data = this.percent_charts_data[3]
          this.percent_charts_5_data = this.percent_charts_data[4]
          // console.log(this.percent_charts_1_data)
          // console.log(this.percent_charts_2_data)
          this.percent_charts_data_all = (this.percent_charts_1_data.n-0)+(this.percent_charts_2_data.n-0)+
              (this.percent_charts_3_data.n-0)+(this.percent_charts_4_data.n-0)+(this.percent_charts_5_data.n-0)
          // console.log(this.percent_charts_data_all)
          // for(let i=0;i<this.line_charts_2_data.length;i++){
          //   this.line_charts_2_data2.push({
          //     value:this.line_charts_2_data[i].amount,
          //     name:this.line_charts_2_data[i].percent,
          //   })
          // }
          this.draw_percent_charts_1();
          this.draw_percent_charts_2();
          this.draw_percent_charts_3();
          this.draw_percent_charts_4();
          this.draw_percent_charts_5();
        }).catch(
        error => console.log(error)
    )

    axios
        .get('http://localhost/business/line_charts_5')
        .then(response => {
          this.line_charts_5_data = response.data
          // console.log(this.percent_charts_data)
          this.draw_pie_charts_2();

          // for(let i=0;i<this.line_charts_2_data.length;i++){
          //   this.line_charts_2_data2.push({
          //     value:this.line_charts_2_data[i].amount,
          //     name:this.line_charts_2_data[i].percent,
          //   })
          // }
        }).catch(
        error => console.log(error)
    )

    axios
        .get('http://localhost/business/line_charts_4')
        .then(response => {
          this.line_charts_3_data = response.data
          // console.log(this.percent_charts_data)
          for(let i=0;i<this.line_charts_3_data.length;i++){
            this.line_charts_3_data2.push({
              value:this.line_charts_3_data[i].n,
              name:this.line_charts_3_data[i].age,
            })
          }
          this.draw_pie_charts_3();
          console.log(this.line_charts_3_data2)
        }).catch(
        error => console.log(error)
    )

  },
  methods:{

    draw_line_charts_1(){
      let line_charts_1 = echarts.init(document.getElementById('line_charts_1'));
      let option = {
        title: {
          text: 'ATM交易金额（单位：万）',
          // subtext: '纯属虚构'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: [
            this.fx_hour_amount[1].name,
            this.fx_hour_amount[2].name,
            this.fx_hour_amount[3].name,
            this.fx_hour_amount[4].name,
            this.fx_hour_amount[5].name,
            this.fx_hour_amount[6].name,
            this.fx_hour_amount[7].name,
          ]
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [
            this.fx_hour_amount[1].value,
            this.fx_hour_amount[2].value,
            this.fx_hour_amount[3].value,
            this.fx_hour_amount[4].value,
            this.fx_hour_amount[5].value,
            this.fx_hour_amount[6].value,
            this.fx_hour_amount[7].value,
          ],
          type: 'line',
          smooth: true
        }]
      };
      line_charts_1.setOption(option)
    },
    draw_line_charts_2(){
      let line_charts_1 = echarts.init(document.getElementById('line_charts_2'));
      let option = {
        title: {
          text: '外汇交易金额（单位：万）',
          // subtext: '纯属虚构'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: [
            this.atm_hour_amount[1].name,
            this.atm_hour_amount[2].name,
            this.atm_hour_amount[3].name,
            this.atm_hour_amount[4].name,
            this.atm_hour_amount[5].name,
            this.atm_hour_amount[6].name,
            this.atm_hour_amount[7].name,
          ]
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [
            this.atm_hour_amount[1].value,
            this.atm_hour_amount[2].value,
            this.atm_hour_amount[3].value,
            this.atm_hour_amount[4].value,
            this.atm_hour_amount[5].value,
            this.atm_hour_amount[6].value,
            this.atm_hour_amount[7].value,
          ],
          type: 'line',
          smooth: true
        }]
      };
      line_charts_1.setOption(option)
    },
    draw_pie_charts_1(){
      let pie_charts = document.getElementById('pie_charts_1');
      let pieCharts = echarts.init(pie_charts);
      let option = {
        title:{
          text:"交易金额占比",
        },
        tooltip: {
          trigger: 'item'
        },
        // legend: {
        //   top: '5%',
        //   left: 'center'
        // },
        series: [
          {
            name: '交易金额占比',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: true,
            label: {
              normal: {
                position: 'center',
                show : false
              }
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '40',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
      //       var series=[];
      //       for(var i = 0;i<result['listmonth'].length;i++){
      //   series.push({
      //     name: result['listmonth'][i],
      //     type: 'bar',
      //     data: result["listscore"][i]
      //   });
      // }

            data: this.line_charts_2_data2,
                // [
              // {value: 1048, name: '搜索引擎'},
              // {value: 735, name: '直接访问'},
              // {value: 580, name: '邮件营销'},
              // {value: 484, name: '联盟广告'},
              // {value: 300, name: '视频广告'}
            // ],


          }
        ]
      };

      option && pieCharts.setOption(option);
    },
    draw_pie_charts_2(){
      let pie_charts = document.getElementById('pie_charts_2');
      let pieCharts = echarts.init(pie_charts);
      let option = {
        tooltip: {
          trigger: 'item'
        },
        title:{
          text:"外汇币种占比",
        },
        // legend: {
        //   top: '5%',
        //   left: 'center'
        // },

        series: [
          {
            name: '币种',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '40',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              {value: this.line_charts_5_data[0].n, name: this.line_charts_5_data[0].amount},
              {value: this.line_charts_5_data[1].n, name: this.line_charts_5_data[1].amount},
              {value: this.line_charts_5_data[2].n, name: this.line_charts_5_data[2].amount},
              {value: this.line_charts_5_data[3].n, name: this.line_charts_5_data[3].amount},
              {value: this.line_charts_5_data[4].n, name: this.line_charts_5_data[4].amount}
            ]
          }
        ]
      };

      option && pieCharts.setOption(option);
    },
    draw_pie_charts_3(){
      let pie_charts = document.getElementById('pie_charts_3');
      let pieCharts = echarts.init(pie_charts);
      let option = {
        tooltip: {
          trigger: 'item'
        },
        title:{
          text:"交易年龄占比",
        },
        // legend: {
        //   top: '5%',
        //   left: 'center'
        // },

        series: [
          {
            name: '年龄',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '40',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: this.line_charts_3_data2,
          }
        ]
      };

      option && pieCharts.setOption(option);
    },
    // draw_china_map(){
    //   let chartDom = document.getElementById('china_map');
    //   let myChart = echarts.init(chartDom);
    //
    //
    //
    //
    // },
    draw_percent_charts_1(){
      let pie_charts = document.getElementById('percent_charts_1');
      let pieCharts = echarts.init(pie_charts);
      let option = {
        title: {
          text: this.percent_charts_1_data.purpose+":"+this.percent_charts_1_data.percent,  //图形标题，配置在中间对应效果图的80%
          left: "center",
          top: "45%",
          textStyle: {
            color: "rgb(50,197,233)",
            fontSize: 14,
            align: "center"
          },
        },
        series: [
          {
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },

            emphasis: {
              label: {
                show: true,
                fontSize: '40',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              {value: this.percent_charts_1_data.n},
              {value: this.percent_charts_data_all},
            ]
          }
        ]
      };

      option && pieCharts.setOption(option);
    },
    draw_percent_charts_2(){
      let pie_charts = document.getElementById('percent_charts_2');
      let pieCharts = echarts.init(pie_charts);
      let option = {
        title: {
          text: this.percent_charts_2_data.purpose+":"+this.percent_charts_2_data.percent,  //图形标题，配置在中间对应效果图的80%
          left: "center",
          top: "45%",
          textStyle: {
            color: "rgb(50,197,233)",
            fontSize: 14,
            align: "center"
          },
        },
        series: [
          {
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },

            emphasis: {
              label: {
                show: true,
                fontSize: '40',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              {value: this.percent_charts_2_data.n},
              {value: this.percent_charts_data_all},
            ]
          }
        ]
      };

      option && pieCharts.setOption(option);
    },
    draw_percent_charts_3(){
      let pie_charts = document.getElementById('percent_charts_3');
      let pieCharts = echarts.init(pie_charts);
      let option = {
        title: {
          text: this.percent_charts_3_data.purpose+":"+this.percent_charts_3_data.percent,  //图形标题，配置在中间对应效果图的80%
          left: "center",
          top: "45%",
          textStyle: {
            color: "rgb(50,197,233)",
            fontSize: 14,
            align: "center"
          },
        },
        series: [
          {
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },

            emphasis: {
              label: {
                show: true,
                fontSize: '40',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              {value: this.percent_charts_3_data.n},
              {value: this.percent_charts_data_all},
            ]
          }
        ]
      };

      option && pieCharts.setOption(option);
    },
    draw_percent_charts_4(){
      let pie_charts = document.getElementById('percent_charts_4');
      let pieCharts = echarts.init(pie_charts);
      let option = {
        title: {
          text: this.percent_charts_4_data.purpose+":"+this.percent_charts_4_data.percent,  //图形标题，配置在中间对应效果图的80%
          left: "center",
          top: "45%",
          textStyle: {
            color: "rgb(50,197,233)",
            fontSize: 14,
            align: "center"
          },
        },
        series: [
          {
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },

            emphasis: {
              label: {
                show: true,
                fontSize: '40',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              {value: this.percent_charts_4_data.n},
              {value: this.percent_charts_data_all},
            ]
          }
        ]
      };

      option && pieCharts.setOption(option);
    },
    draw_percent_charts_5(){
      let pie_charts = document.getElementById('percent_charts_5');
      let pieCharts = echarts.init(pie_charts);
      let option = {
        title: {
          text: this.percent_charts_5_data.purpose+":"+this.percent_charts_5_data.percent,  //图形标题，配置在中间对应效果图的80%
          left: "center",
          top: "45%",
          textStyle: {
            color: "rgb(50,197,233)",
            fontSize: 14,
            align: "center"
          },
        },
        series: [
          {
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },

            emphasis: {
              label: {
                show: true,
                fontSize: '40',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              {value: this.percent_charts_5_data.n},
              {value: this.percent_charts_data_all},
            ]
          }
        ]
      };

      option && pieCharts.setOption(option);
    },
  }
}
</script>

<style scoped>
.south{
  margin-top: 60px;
}
</style>