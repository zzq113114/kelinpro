<template>
    <div class="sys-page" v-once>
        <app-title title="数据统计">

        </app-title>
        <div id="chart1" class="chart-area"></div>
        <div id="chart2" class="chart-area"></div>
        <div id="chart3" class="chart-area"></div>
        <div id="chart4" class="chart-area"></div>
    </div>
</template>

<script>
    var aa=[];
    var chartnum3=[];
    var chartnum4=[];
    var chart3money=[];
    var chart3num=[];
    import lodash from 'lodash'
    import echarts from 'echarts'
    import '@/util/echarts.theme.default'
    var chartnum=[];
    var chartnum2=[];
    var chart4num=[];
    var chart4num2=[];
    export default {
        name: 'home',

        data() {
            return {
                num:{
                    number:[]
                },
                chart: {
                    target: null,
                    data: [10000, 8000, 12000, 18000, 7000, 10000, 14000],

                    option: {
                        title: {
                            text: '2020年可蔺月财报',
                            subtext: '月份财报'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'cross',
                                label: {
                                    backgroundColor: '#283b56'
                                }
                            }
                        },
                        legend: {
                            data: ['实付金额', '预购金额']
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                dataView: {readOnly: false},
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        dataZoom: {
                            show: false,
                            start: 0,
                            end: 100
                        },
                        xAxis: [
                            {
                                type: 'category',
                                boundaryGap: true,
                                data: chartnum
                            },
                            {
                                type: 'category',
                                boundaryGap: true,
                                data:
                                chartnum3
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                scale: true,
                                name: '价格',
                                max: 3000,
                                min: 0,
                                boundaryGap: [0.2, 0.2]
                            },
                            {
                                type: 'value',
                                scale: true,
                                name: '预购量',
                                max: 12000,
                                min: 0,
                                boundaryGap: [0.2, 0.2]
                            }
                        ],
                        series: [
                            {
                                name: '实付金额',
                                type: 'bar',
                                xAxisIndex: 1,
                                yAxisIndex: 1,
                                barWidth : 30,
                                data:chartnum2

                            },
                            {
                                name: '预购金额',
                                type: 'line',
                                data: chartnum4

                            }
                        ]
                    }
                },
                chart2: {
                    target: null,
                    option: {
                        title: {
                            text: '订单收拒数量信息',
                            subtext: '可蔺',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: '{a} <br/>{b} : {c} ({d}%)'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                            data: ['成交数', '作废数', '订单作废数', '重派数',"审核作废数","未知"]
                        },
                        series: [
                            {
                                name: '访问来源',
                                type: 'pie',
                                radius: '55%',
                                center: ['50%', '60%'],
                                data: aa
                                ,
                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    }
                },
                chart3: {
                    target: null,
                    data: [10000, 8000, 12000, 18000, 7000, 10000, 14000],
                    option: {
                        title: {
                            text: '最近7天收入',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'axis',

                        },
                        toolbox: {
                            show: true,
                            feature: {
                                dataView: {readOnly: false},
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        dataZoom: {
                            show: false,
                            start: 0,
                            end: 100
                        },
                        xAxis: {
                            scale: true,
                            type: 'category',
                            data: chart3num
                        },
                        yAxis: {
                            scale: true,
                            type: 'value'
                        },
                        series: [{
                            data: chart3money,
                            type: 'line'
                        }]
                    }
                },
                chart4: {
                    target: null,
                    data: [12, 12, 2, 2, 3, 4, 2],
                    option: {
                        tooltip: {
                            trigger: 'axis',

                        },
                        title: {
                            text: '订单来源',
                            left: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: true,
                            data: chart4num
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: chart4num2,
                            boundaryGap: true,
                            type: 'bar'
                        }]
                    }
                }
            }
        },
        props: {
            chartData: Array
        },
        watch: {
            chartData() {

                if (this.chartData.length) {
                    this.chart.data = this.chartData
                    this.chart2.data = this.chartData
                    this.chart3.data = this.chartData
                    this.chart4.data = this.chartData
                }
            },
            'chart.data'() {
                this.draw()
            },
        },
        mounted() {
            this.$nextTick(() => {
                this.chartsInit()
            })
        },
        created(){
          this.selectOrderNumber();
          this.selectOrderFinishNumber();
          this.selectFirstOrderFinishNumber();
          this.selectSevenOrderNumber();
          this.selectSevenOrderNumber2();
          this.selectOrinigOrderNumber();
        },
        methods: {
            selectOrderNumber(){
                var nums="";
                this.$ajax({
                    method :"GET",
                    url : this.GLOBAL.REQUEST_HOST + "order/selectOrderNumber",
                }).then(function (res) {

                    var newNum=[];
                    if(res.status==200){
                        aa=[];
                        var rest=res.data;
                        for(var j = 0;j < rest.length;  j++) {
                            nums ={"value":parseInt(rest[j].value),"name":rest[j].name};
                            newNum.push(nums)
                        }
                         aa= newNum;
                    }
                });
            },
            selectOrderFinishNumber(){

                this.$ajax({
                    method :"GET",
                    url : this.GLOBAL.REQUEST_HOST + "order/selectOrderFinishNumber",
                }).then(function (res) {


                    if(res.status==200){
                        chartnum=[];
                        chartnum2=[];
                        var rest=res.data;

                        for(var j = 0;j < rest.length;  j++) {
                            chartnum.push(rest[j].mth)
                            chartnum2.push(rest[j].money)
                        }

                    }
                });
            },
            selectFirstOrderFinishNumber(){

                this.$ajax({
                    method :"GET",
                    url : this.GLOBAL.REQUEST_HOST + "order/selectFirstOrderFinishNumber",
                }).then(function (res) {

                    if(res.status==200){
                        chartnum3=[];
                        chartnum4=[];
                        var rest=res.data;
                        for(var j = 0;j < rest.length;  j++) {
                            chartnum3.push(rest[j].mth)
                            chartnum4.push(rest[j].money)
                        }

                    }
                });
            },
            selectSevenOrderNumber(){

                this.$ajax({
                    method :"GET",
                    url : this.GLOBAL.REQUEST_HOST + "order/selectSevenOrderNumber",
                }).then(function (res) {
                    if(res.status==200){
                        chart3money=[];
                        var rest=res.data;
                        for(var j = 0;j < rest.length;  j++) {
                            chart3money.push(rest[j].money)
                        }

                    }
                });
            },
            selectSevenOrderNumber2(){

                this.$ajax({
                    method :"GET",
                    url : this.GLOBAL.REQUEST_HOST + "order/selectSevenOrderNumber2",
                }).then(function (res) {
                    if(res.status==200){
                        chart3num=[];
                        var rest=res.data;
                        for(var j = 0;j < rest.length;  j++) {
                            chart3num.push(rest[j].mth)
                        }

                    }
                });
            },
            selectOrinigOrderNumber(){

                this.$ajax({
                    method :"GET",
                    url : this.GLOBAL.REQUEST_HOST + "order/selectOrinigOrderNumber",
                }).then(function (res) {

                    if(res.status==200){
                        chart4num=[];
                        chart4num2=[];
                        var rest=res.data;
                        for(var j = 0;j < rest.length;  j++) {

                            chart4num.push(rest[j].name)
                            chart4num2.push(rest[j].value)

                        }

                    }
                });
            },
            // 初始化图表
            chartsInit() {
                // // 创建图表对象
                if (!this.chart.target) {
                    debugger
                    this.chart.target = echarts.init(document.getElementById('chart1'), 'westeros')
                }
                if (!this.chart2.target) {
                    debugger
                    this.chart2.target = echarts.init(document.getElementById('chart2'), 'westeros')

                }
                if (!this.chart3.target) {
                    debugger
                    this.chart3.target = echarts.init(document.getElementById('chart3'), 'westeros')
                }
                if (!this.chart4.target) {
                    this.chart4.target = echarts.init(document.getElementById('chart4'), 'westeros')
                }
                debugger
                // 绘制默认图表
                this.chart.target.setOption(this.chart.option)
                this.chart2.target.setOption(this.chart2.option)
                this.chart3.target.setOption(this.chart3.option)
                this.chart4.target.setOption(this.chart4.option)

            },
            // 重绘
            draw() {
                // 配置项需要变更
                let option = {
                    series: [
                        {
                            data: this.chart.data
                        }
                    ]
                }
                lodash.defaultsDeep(option, this.chart.option)
                lodash.defaultsDeep(option, this.chart2.option)
                lodash.defaultsDeep(option, this.chart3.option)
                lodash.defaultsDeep(option, this.chart4.option)
                this.chart.target.setOption(option)
                this.chart2.target.setOption(option)
                this.chart3.target.setOption(option)
                this.chart4.target.setOption(option)

            }

        }

    }
</script>

<style lang="scss" scoped>
    .chart-area {
        width: 50%;
        height: 300px;
        float: left;
    }
</style>
