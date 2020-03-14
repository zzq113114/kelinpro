<template>
    <div class="sys-page">
        <div class="page-content">
            <!-- <app-search> -->
            <el-form :inline="true" ref="searchform" :model="searchChoice"   label-width="100px">
                <el-form-item label="订单流水号">
                    <el-col :span="10">
                        <el-input v-model="searchChoice.orderId"  style="width:200%"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <div class="grid-content bg-purple-dark">
                        <el-button type="primary"  @click="selectFinishOrder">查询</el-button>
                    </div>
                </el-form-item>
            </el-form>
            <app-section>
                <el-table :data="tableData"  style="width: 100%" @selection-change="handleSelectionChange" >
                    <!-- <el-table-column type="selection" width="55"></el-table-column> -->
                    <el-table-column prop="orderNo" label="订单流水号"></el-table-column>
                    <el-table-column prop="customerName" label="下单人"></el-table-column>
                    <el-table-column prop="customerAddress" show-overflow-tooltip  label="客户地址"></el-table-column>
                    <el-table-column prop="mobileNo" label="预定电话"></el-table-column>
                    <el-table-column prop="detailInfo" label="订单详情"></el-table-column>
                    <el-table-column prop="statusName" label="订单状态"></el-table-column>
                    <el-table-column prop="staffsName" label="派单员工"></el-table-column>
                    <el-table-column  label="操作">
                        <template slot-scope="scope">
                            <el-button   size="small" type="primary" @click="finishSubmit(scope.row)">核实</el-button>
                            <el-button  size="small" type="primary" @click="callBackOrder(scope.row)">驳回</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <!--<el-pagination background layout="total,prev, pager, next" @current-change="pageChange" :total="pageDate.totalCount" :page-size="pageDate.pageSize" >-->
                <!--</el-pagination>-->
                <el-pagination background layout="total,prev, pager, next" @current-change="pageChange"
                               :total="pageData.totalCount" :page-size="pageData.pageSize">
                </el-pagination>
            </app-section>
        </div>
        </div>
</template>

<script>
    export default {
        name: "orderJudge",
        data() {
            return {
                pageData:{
                  totalCount:0,
                  pageNo:1,
                  pageSize: 15
                },
                searchChoice: {
                    orderId: "",
                    status: "",
                    pageNo: 0,
                    pageSize: 0
                },
                addModel: {
                    orderId: "",
                    status: "",
                    orderNo:'',
                    customerName:'',
                    customerAddress:'',
                    mobileNo:'',
                    statusName:''
                },
                tableData:[{
                    orderNo:'',
                    customerName:'',
                    customerAddress:'',
                    mobileNo:'',
                    statusName:''
                }]
            }
        },
        computed:{

        },
        created(){
            this.selectFinishOrder()
        },
        methods:{
            searchClick() {
                alert("ok");
            },
            selectFinishOrder(){

                let _self = this;
                this.searchChoice.pageNo=this.pageData.pageNo
                this.searchChoice.pageSize= this.pageData.pageSize
                this.$ajax({
                    method :"POST",
                    url : this.GLOBAL.REQUEST_HOST + "order/judgeFinishOrder",
                    data: this.searchChoice
                }).then(function (res) {
                    debugger
                    if(res.data.code==2000){
                        debugger
                        _self.tableData = res.data.data.list;
                        console.log(_self.tableData)
                        _self.pageData.totalCount = res.data.data.size

                    }
                });
            },
            finishSubmit(row){ //提交订单分派信息

                let _self = this;
                let orderId = row.id;
                this.$ajax({
                    method: 'GET',
                    url:this.GLOBAL.REQUEST_HOST + "order/checkOrder/"+orderId,
                }).then(function(res){
                    if (res.data.code == 2000){
                        _self.dialog.dispatchModel.show = false;
                        _self.selectOrderList();
                        this.selectFinishOrder();
                    }
                }).catch(e=>console.log(e))
            },
            callBackOrder(row){ //提交订单分派信息

                let _self = this;
                let orderId = row.id;
                this.$ajax({
                    method: 'GET',
                    url:this.GLOBAL.REQUEST_HOST + "order/callBackOrder/"+orderId,
                }).then(function(res){
                    if (res.data.code == 2000){
                        _self.dialog.dispatchModel.show = false;
                        _self.selectOrderList();

                    }
                }).catch(e=>console.log(e))
            },
            handleSelectionChange(val) {

                this.addModel.items = val;
            },
            pageChange(val){

                this.pageData.pageNo=val
                this.selectFinishOrder();
            }
        },


    }
</script>

<style created>

</style>
