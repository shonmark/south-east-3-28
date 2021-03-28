let attackWay = { // 袭击方式翻译
    "1": "Assassination",
    "2": "Armed Assault",
	"3": "Bombing/Explosion",
	"4": "Hijacking",
	"5": "Hostage Taking (Barricade Incident)",
	"6": "Hostage Taking (Kidnapping)",
	"7": "Facility/Infrastructure Attack",
	"8": "Unarmed Assault",
	"9": "Unknown"
}
$(function(){
    /**
     * @description
     * 世界地图
     */
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById('main')); 
    let option = {
        // 设置地图画布样式
        backgroundColor: new echarts.graphic.RadialGradient(0.5, 0.5, 0.4, [{
            offset: 0,
            color: '#4b5769'
        }, {
            offset: 1,
            color: '#404a59'
        }]),
        // 设置地图标题信息样式
        title : {
            text: '世 界 恐 怖 袭 击 事 件 统 计',
            left: 'center',
            top: 20,
            textStyle: {
                color: '#eee'
            }
        },
        series : [
            {
                name: 'World Country',
                type: 'map',
                mapType: 'world',
                selectedMode : 'single',  // 选中固定样式
                roam: true,
                itemStyle: {
                    emphasis: {                 // 也是选中样式
                        borderWidth: 1,
                        borderColor: '#00ffff',
                        color: '#ffffff',
                        label: {
                            show: true,
                            textStyle: {
                                color: '#ff0000'
                            }
                        }
                    }
                },
                data: [
                    {name:'China', selected:true} // selected:true 默认选中
                ]
            }
        ]
    };
    myChart.setOption(option);  
    
    // 点击地图查看数据变化
    myChart.on('click', function (params) {
        console.log(params.name)
        let abbrKey = confirmKey(params.name) || '';
        // 折线图
        lineOption.title.text = translateCountryName(abbrKey) + '近年恐怖袭击事件统计';
        lineOption.series[0].data = getAttackNum(abbrKey);
        lineChart.setOption(lineOption, true);  
        // 饼图
        pieOption.series[0].data = getAttackWay(abbrKey).value;
        pieOption.legend.data = getAttackWay(abbrKey).type;
        pieChart.setOption(pieOption, true);  
        // 柱状图
        barOption.series[0].data = getTollNum(abbrKey);
        barChart.setOption(barOption, true);  
    });

    /**
     * @description 恐怖袭击次数折线图
     */
    let lineChart = echarts.init(document.getElementById('line')); 
    let lineOption = {
        tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        },
        title: {
            text: '中国近年恐怖袭击事件统计',
            left: '50px'
        },
        grid: {
            top: 70,
            bottom: 50,
            left: 100
        },
        xAxis: [{
            type: 'category',
            data: setLinexAxis(),
            axisPointer: {
                label: {
                    formatter: function (params) {
                        return params.value
                            + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                    }
                }
            },
        }],
        yAxis: {
            type: 'value'
        },
        series: [{
            smooth: true,
            data: getAttackNum('CHN'),
            type: 'line'
        }]
    }
    lineChart.setOption(lineOption);  

    /**
     * @description 恐怖袭击方式占比
     */
    let pieChart = echarts.init(document.getElementById('pie')); 
    let pieOption = {
        title: {
            text: '恐怖袭击方式占比',
            top: 350,
            left: 200
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: getAttackWay('CHN').type
        },
        series: [
            {
                name:'袭击类型',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: getAttackWay('CHN').value
            }
        ]
    }
    pieChart.setOption(pieOption);  

    /**
     * @description 损失伤亡
     */

    let barChart = echarts.init(document.getElementById('bar')); 
    let barOption = {
        title: {
            text: '伤亡人数统计',
            left: '50px'
        },
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data: setLinexAxis(),
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'伤亡人数',
                type:'bar',
                barWidth: '80%',
                data:getTollNum('CHN')
            }
        ]
    }
    barChart.setOption(barOption);  
})

/**
 * 确定国家缩写键名
 */
function confirmKey(engName) {
    for (let key in countryKey) {
        if (countryKey[key] === engName) {
            return key;
            break;
        }
    }
}

/**
 * 翻译国家中文名
 */
function translateCountryName(abbrName) {
    return nameJson[abbrName] || abbrName;
}

/**
 * 统计对应国家年月下的袭击次数
 */
function getAttackNum(country) {
    let attackJson = {};
    for (let i=0; i<totalData.length; i++) {
        let item = totalData[i];
        if (item.country === country) {
            let date = item.iyear + '.' + item.imonth;
            if (!attackJson[date]) {
                attackJson[date] = 1;
            } else {
                attackJson[date] ++;
            }
        }
    }
    let xaxis = setLinexAxis();
    let yaxis = [];
    for (let i=0; i<xaxis.length; i++) {
        yaxis.push(attackJson[xaxis[i]] || 0);
    }
    return yaxis;
}

/**
 * 设置折线图横坐标
 */
function setLinexAxis() {
    let lineSeriseArr = [];
    for (let year in lineSeriseData) {
        for (let i=1; i<=12; i++) {
            lineSeriseArr.push(year.split('_')[1] + '.' + i.toString());
        }
    }
    return lineSeriseArr;
}

/**
 * 统计对应国家不同袭击方式的次数
 */
function getAttackWay(country) {
    let attackJson = {};
    for (let i=0; i<totalData.length; i++) {
        let item = totalData[i];
        if (item.country === country) {
            let way = item.attacktype1;
            if (!attackJson[way]) {
                attackJson[way] = 1;
            } else {
                attackJson[way] ++;
            }
        }
    }
    let pieType = [];
    let pieValue = [];
    let i = 0;
    for (let key in attackJson) {
        pieType.push(key);
        pieValue[i] = {};
        pieValue[i].value = attackJson[key];
        pieValue[i].name = attackWay[key];
        i++;
    }
    return {
        type: translateWay(pieType),
        value: pieValue
    }
}

/**
 * 翻译袭击方式
 */
function translateWay(attackType) {
    let attackVal = [];
    for (let i=0; i<attackType.length; i++) {
        attackVal.push(attackWay[attackType[i]])
    }
    return attackVal;
}

/**
 * 统计对应国家的伤亡人数
 */
function getTollNum(country) {
    let attackJson = {};
    for (let i=0; i<totalData.length; i++) {
        let item = totalData[i];
        if (item.country === country) {
            let date = item.iyear + '.' + item.imonth;
            if (!attackJson[date]) {
                attackJson[date] = item.nwound;
            } else {
                attackJson[date] += item.nwound;
            }
        }
    }
    let xaxis = setLinexAxis();
    let yaxis = [];
    for (let i=0; i<xaxis.length; i++) {
        yaxis.push(attackJson[xaxis[i]] || 0);
    }
    return yaxis;
}