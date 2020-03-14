
<template>
<div>
    <div id="chart1" class="chart-area"></div>
    <div id="chart2" class="chart-area2"></div>
</div>

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
                    data: [10000,8000,12000,18000,7000,10000,14000],
                    option : {
                        xAxis: {
                            type: 'category',
                            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: [820, 932, 901, 934, 1290, 1330, 1320],
                            type: 'line'
                        }]
                    }
          }

            }
        },

        props: {
            chartData: Array
        },
        watch: {
            chartData(){
                if(this.chartData.length){
                     this.chart.data = this.chartData
                   this.chart2.data = this.chartData

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
                    this.chart2.target = echarts.init(document.getElementById('chart2'), 'westeros')
                }
                // 绘制默认图表
                this.chart.target.setOption(this.chart.option)
                this.chart2.target.setOption(this.chart.option)
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
                this.chart.target.setOption(option)
                this.chart2.target.setOption(option)
            }
        }
    }
</script>

<style lang="scss" scoped>
    .chart-area{
        width: 50%;
        height: 300px;
        float: left ;
    }
</style>
