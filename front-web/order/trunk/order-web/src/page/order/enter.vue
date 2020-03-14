<template>

    <div>
        <app-title title="订单录入"></app-title>
        <el-form  :model="addModel"   :rules="orderRules" ref="addModel" :inline="true" abel-width="100%">
            <el-form-item label="接单员"  prop="createUser">
                <el-input v-model="addModel.createUser" auto-complete="off" label-width="100%"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" prop="mobileNo">
                <el-input v-model="addModel.mobileNo" auto-complete="off" label-width="100%"></el-input>
            </el-form-item>
            <el-form-item label="人数" prop="staffCount">
                <el-input v-model.number="addModel.staffCount" auto-complete="off" label-width="100%"></el-input>
            </el-form-item>
            <el-form-item label="下单日期" prop="createDate">
                <el-date-picker v-model="addModel.createDate"  type="datetime" placeholder="选择日期时间"></el-date-picker>
            </el-form-item>

            <el-form-item label="预约日期" prop="bookStartDate">
                <el-date-picker v-model="addModel.bookStartDate" type="datetime" placeholder="选择日期时间"></el-date-picker>
            </el-form-item>
            <el-form-item label="下单人"  prop="customerName">
                <el-input v-model="addModel.customerName" label-width="100%"></el-input>
            </el-form-item>
            <el-form-item label="工作时长" prop="workHours">
                <el-input v-model.number="addModel.workHours"  auto-complete="off" label-width="100%"></el-input>
            </el-form-item>
            <el-form-item label="金额" prop="amount">
                <el-input v-model="addModel.amount" auto-complete="off" label-width="100%"></el-input>
            </el-form-item>

            <el-form-item label="服务内容" prop="items">
                <el-select  v-model="addModel.itemsName"  multiple placeholder="请选择">
                    <el-option v-for="item in itemOpts" :key="item.id" :label="item.itemName" :value="item.id" label-width="100%"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="信息来源" prop="items">
                <el-select v-model="addModel.origin" placeholder="请选择">
                    <el-option v-for="item in originOpts" :key="item.id" :label="item.originFrom" :value="item.id" label-width="100%"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="下单地址" prop="customerAddress">
                <el-input v-model="addModel.customerAddress" auto-complete="off" label-width="100%"></el-input>
            </el-form-item>
            <el-form-item label="备注" prop="remark">
                <el-input  v-model="addModel.remark"  auto-complete="off" label-width="100%"></el-input>
            </el-form-item>
        </el-form>
        <div class="textC">
            <el-button type="primary" @click="addSubmit('addModel')">保存</el-button>
            <el-button type="primary">取消</el-button>
        </div>
    </div>


</template>

<script>
    export default {
        data() {
            return {
                pageData: {
                    totalCount: 0,
                    pageNo: 1,
                    pageSize: 10
                },

                currentDate: new Date(),
                searchData: {
                    startDate: [],
                    customerName: "",
                    orderStatus: "",
                    pageNo: 0,
                    pageSize: 0
                },
                originOpts: [],
                itemOpts: [],
                addModel: {
                    createUser: '',
                    createDate: '',
                    bookStartDate: '',
                    customerName: '',
                    mobileNo: "",
                    staffCount: '',
                    workHours: '',
                    customerAddress: '',
                    remark: '',
                    itemsName: [],
                    origin: '',
                    amount: ''
                },
                orderRules: {
                    bookStartDate: [
                        {type: 'date', required: true, message: '请输入预约日期', trigger: 'change'}
                    ],
                    customerName: [
                        {required: true, message: '请输入客户名称', trigger: 'blur'}
                    ],
                    mobileNo: [
                        {required: true, message: '请输入联系电话', trigger: 'blur'}
                    ],
                    customerAddress: [
                        {required: true, message: '请输入客户联系地址', trigger: 'blur'}
                    ],
                    staffCount: [
                        {required: true, message: '请输入人数'},
                        {type: 'number', message: '人数必须是数字'}
                    ],
                    workHours: [
                        {required: true, message: '请输入工作时长'},
                        {type: 'number', message: '工作时长必须是数字'}
                    ]
                }
            }
        },
        created() {
            this.loadServiceItems();
            this.selectOriginList();
        },
        methods: {
            searchClick() {

            },
            open5() {
                this.$alert('订单添加成功', '', {
                    dangerouslyUseHTMLString: true
                });
            },

            clearAddModel() {
                return {
                    addModel: {
                        createDate: '',
                        bookStartDate: '',
                        customerName: '',
                        mobileNo: "",
                        staffCount: '',
                        workHours: '',
                        customerAddress: '',
                        detailInfo: '',
                        items: [],
                        origin: '',
                        amount: ''
                    }
                }
            },
            loadServiceItems() {
                let _self = this;
                this.$ajax({
                    method: "get",
                    url: this.GLOBAL.REQUEST_HOST + "company/serviceItems"
                }).then(function(res) {
                    if (res.data.code == 2000) {
                        _self.itemOpts = res.data.data;
                    }
                });
            },
            selectOriginList(){
                let _self = this;
                this.$ajax({
                    method: "GET",
                    url: this.GLOBAL.REQUEST_HOST+"maintenanc/list"
                }).then(function(res){
                    if (res.data.code == 2000){
                        _self.originOpts = res.data.data;
                    }
                }).catch(e=>console.log(e));
            },
            addSubmit(formName) {
                this.$refs[formName].validate((valid)=>{
                    if (valid){
                        this.$ajax({
                            method: "POST",
                            url: this.GLOBAL.REQUEST_HOST + "order/add",
                            data: this.addModel
                        }).then(function(res) {
                            if (res.data.code == 2000) {

                            }else {
                                alert("添加失败")
                            }
                        });
                        this.open5();
                        this.addModel = this.clearAddModel();
                    }
                })

            },

        }
    }
</script>

<style scoped>

</style>
