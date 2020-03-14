<template>
    <div id="chart1" class="chart-area"></div>
</template>

<script>
import lodash from 'lodash'
import echarts from 'echarts'
import '@/util/echarts.theme.default'

export default {
    data() {
        return {
            chart: {
                target: null,
                data: [
                    '10000','8000','15000','12000','17000','9000','14000'
                ],
                option: {
                    tooltip: {
                        trigger: 'axis',
                        formatter: '{a}<br />{b} : {c}'
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: ['周一2', '周二', '周三', '周四', '周五', '周六', '周日']
                    },
                    yAxis: {
                        type: 'value',
                        min: 0,
                        max: 100,
                    },
                    series: [
                        {
                            name: '邮件营销',
                            type: 'line',
                            stack: '总量',
                            data: [10000,8000,15000,15000,17000,9000,14000]
                        }
                    ]
                }
                // option :{
                //     legend: {},
                //     tooltip: {},
                //     dataset: {
                //         source: [
                //             ['product', '2015', '2016', '2017'],
                //             ['Matcha Latte', 43.3, 85.8, 93.7],
                //             ['Milk Tea', 83.1, 73.4, 55.1],
                //             ['Cheese Cocoa', 86.4, 65.2, 82.5],
                //             ['Walnut Brownie', 72.4, 53.9, 39.1]
                //         ]
                //     },
                //     xAxis: {type: 'category'},
                //     yAxis: {},
                //     // Declare several bar series, each will be mapped
                //     // to a column of dataset.source by default.
                //     series: [
                //         {type: 'bar'},
                //         {type: 'bar'},
                //         {type: 'bar'}
                //     ]
                // }
            }
        }
    },
    props: {
        chartData: Array
    },
    watch: {
        chartData(){
            if(this.chartData.length){
                return this.chart.data = this.chartData
            }
        },
        'chart.data'(){
            this.draw()
        }
    },
    mounted(){
        this.$nextTick(() => {
            this.chartsInit()
        })
    },
    methods: {
        // 初始化图表
        chartsInit(){
            // 创建图表对象
            if(!this.chart.target){
                this.chart.target = echarts.init(document.getElementById('chart1'), 'westeros')
            }
            // 绘制默认图表
            this.chart.target.setOption(this.chart.option)
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
            this.chart.target.setOption(option)
        }
    }
}
</script>

<style lang="scss" scoped>
.chart-area{
    width: 100%;
    height: 400px;
}
</style>
