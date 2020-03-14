<template>
  <div class="sys-page">
    <div class="page-content">
      <!-- <app-search> -->
      <el-form :inline="true" ref="searchform" :model="searchData" label-width="100px">
        <el-form-item label="下单时间">
         <el-date-picker v-model="searchData.startDate"
         type="datetimerange"
         range-separator="至" start-placeholder="开始日期"
         value-format="yyyy-MM-dd HH:mm:ss"
         end-placeholder="结束日期"></el-date-picker>
        </el-form-item>
        <el-form-item label="下单人">
          <el-col :span="10">
           <el-input v-model="searchData.customerName"  style="width:200%"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="订单状态">
          <el-col :span="15">
           <el-select v-model="searchData.orderStatus">
             <el-option label="新建" value="1"></el-option>
             <el-option label="已分派" value="2"></el-option>
             <el-option label="分派成功" value="3"></el-option>
             <el-option label="分派失败" value="4"></el-option>
             <el-option label="订单结束" value="5"></el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item>
          <div class="grid-content bg-purple-dark">
            <el-button type="primary" @click="selectOrderList">查询</el-button>
          </div>
        </el-form-item>
      </el-form>
      <!-- </app-search> -->
      <el-row>
        <el-col :span="24">
          <div class="grid-content bg-purple-dark">
            <el-button type="primary" @click="showAddModelView">增加订单</el-button>
          </div>
        </el-col>
      </el-row>
      <app-section>
        <el-table :data="tableData"  style="width: 100%" @selection-change="handleSelectionChange">
          <!-- <el-table-column type="selection" width="55"></el-table-column> -->
            <el-table-column prop="orderNo" label="订单流水号"></el-table-column>
          <el-table-column prop="customerName" label="下单人"></el-table-column>
          <el-table-column prop="customerAddress" show-overflow-tooltip label="客户地址"></el-table-column>
          <el-table-column prop="mobileNo" label="预定电话"></el-table-column>
            <el-table-column prop="bookStartDate" show-overflow-tooltip label="预约日期"></el-table-column>
          <el-table-column prop="detailInfo" show-overflow-tooltip label="订单详情"></el-table-column>
          <el-table-column prop="statusName" label="订单状态"></el-table-column>
          <el-table-column  label="操作">
            <template slot-scope="scope">
              <el-button v-if="isEditShow(scope.row)" size="small" type="primary" @click="showEditModelView(scope.row)">修改</el-button>
              <el-button v-if="isDisShow(scope.row)" size="small" type="primary" @click="showDispatchOrder(scope.row)">分派</el-button>
              <el-button v-if="isCancelShow(scope.row)" size="small" type="danger" @click="cancelOrder(scope.row)">作废</el-button>
              <el-button v-if="isResetShow(scope.row)" size="small" type="primary" @click="resetOrder(scope.row)">重新下派</el-button>
              <!-- <el-button v-if="scope.row.status==2" size="small" type="primary" @click="showFinishOrder(scope.row)">完成</el-button> -->
            </template>
          </el-table-column>
        </el-table>
        <el-pagination background layout="total,prev, pager, next" @current-change="pageChange"
        :total="pageData.totalCount" :page-size="pageData.pageSize">
        </el-pagination>
      </app-section>
    </div>
    <el-dialog title="新增订单" :visible.sync="dialog.addModel.show" :modal-append-to-body="false" custom-class="editPawDialog">
      <el-form :model="addModel" :rules="orderRules" ref="addModel" label-width="200px">
        <el-form-item label="接单人员" prop="createUser">
          <el-input v-model="addModel.createUser" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="下单日期" prop="createDate">
           <el-date-picker  v-model="addModel.createDate"  type="datetime"   placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="预约日期" prop="bookStartDate">
           <el-date-picker v-model="addModel.bookStartDate"  type="datetime"   placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="下单人员" prop="customerName">
          <el-input v-model="addModel.customerName" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="下单电话" prop="mobileNo">
          <el-input v-model="addModel.mobileNo" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="人数" prop="staffCount">
          <el-input v-model.number="addModel.staffCount" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="预约时长" prop="workHours">
          <el-input v-model.number="addModel.workHours" auto-complete="off" style="width:30%"></el-input><span>/小时</span>
        </el-form-item>
        <el-form-item label="服务内容" prop="items" >
          <el-select v-model="addModel.itemsName" multiple placeholder="请选择">
            <el-option v-for="item in itemOpts" :key="item.id" :label="item.itemName" :value="item.itemName" style="width:50%"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="下单地址" prop="customerAddress">
          <el-input v-model="addModel.customerAddress" auto-complete="off" style="width:70%"></el-input>
        </el-form-item>
        <el-form-item label="信息来源" prop="items" >
          <el-select v-model="addModel.origin" placeholder="请选择">
            <el-option v-for="item in originOpts" :key="item.id" :label="item.originFrom" :value="item.id" style="width:50%"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="金额" prop="amount">
          <el-input v-model="addModel.amount" auto-complete="off" style="width:20%"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input type="textarea" v-model="addModel.remark" auto-complete="off" style="width:70%"></el-input>
        </el-form-item>
      </el-form>
      <div class="textC">
        <el-button type="primary" @click="addSubmit('addModel')">保存</el-button>
        <el-button type="primary" @click="cancel">取消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="订单修改" :visible.sync="dialog.editModel.show" :modal-append-to-body="false" custom-class="editPawDialog">
      <el-form :model="addModel" :rules="orderRules" ref="addModel" label-width="200px">
        <el-form-item label="接单人员" prop="createUser">
          <el-input v-model="addModel.createUser" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="下单日期" prop="createDate">
           <el-date-picker  v-model="addModel.createDate"  type="datetime"   placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="预约日期" prop="bookStartDate">
           <el-date-picker v-model="addModel.bookStartDate"  type="datetime"   placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="下单人员" prop="customerName">
          <el-input v-model="addModel.customerName" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="下单电话" prop="mobileNo">
          <el-input v-model="addModel.mobileNo" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="人数" prop="staffCount">
          <el-input v-model.number="addModel.staffCount" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="预约时长" prop="workHours">
          <el-input v-model.number="addModel.workHours" auto-complete="off" style="width:30%"></el-input><span>/小时</span>
        </el-form-item>
        <el-form-item label="服务内容" prop="itemsName" >
          <el-select v-model="addModel.items" multiple placeholder="请选择">
            <el-option v-for="item in itemOpts" :key="item.id" :label="item.itemName" :value="item.id" style="width:50%"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="下单地址" prop="customerAddress">
          <el-input v-model="addModel.customerAddress" auto-complete="off" style="width:70%"></el-input>
        </el-form-item>
        <el-form-item label="信息来源" prop="items" >
          <el-select v-model="addModel.origin" placeholder="请选择">
            <el-option v-for="item in originOpts" :key="item.id" :label="item.originFrom" :value="item.id" style="width:50%"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="金额" prop="amount">
          <el-input v-model="addModel.amount" auto-complete="off" style="width:20%"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input type="textarea" v-model="addModel.remark" auto-complete="off" style="width:70%"></el-input>
        </el-form-item>
      </el-form>
      <div class="textC">
        <el-button type="primary" @click="editSubmit('addModel')">保存</el-button>
        <el-button type="primary" @click="editCancel">取消</el-button>
      </div>
    </el-dialog>
    <!-- 下派订单对话框-->
    <el-dialog title="订单下派" :visible.sync="dialog.dispatchModel.show" :modal-append-to-body="false" width="60%">
       <el-form :inline="true" :model="dispatchModel" label-width="100px" >
         <el-form-item label="选择员工">
          <el-col >
           <el-select v-model="dispatchModel.staffIds" multiple placeholder="请选择" style="width:200%">
            <el-option v-for="item in staffItems" :key="item.id" :label="item.staffName" :value="item.id" style="width:100%"></el-option>
          </el-select>
          </el-col>
         </el-form-item>
       </el-form>
       <div class="textC">
        <el-button type="primary" @click="dispatchSubmit">确定</el-button>
        <el-button type="primary" @click="staffDialogCancel">取消</el-button>
      </div>
    </el-dialog>
    <!-- 完成订单对话框-->
    <el-dialog title="订单完成" :visible.sync="dialog.finishModel.show" :modal-append-to-body="false" width="60%">
       <el-form :inline="true" :model="finishModel" label-width="200px" >
         <el-form-item label="订单状态">
          <el-select v-model="finishModel.status" placeholder="请选择" style="width:100%">
             <el-option v-for="item in finishOrderItems" :key="item.status" :label="item.title" :value="item.status" style="width:100%"></el-option>
          </el-select>
         </el-form-item>
         <el-form-item label="金额">
           <el-input v-model="finishModel.incomeAmount" auto-complete="off" style="width:100%"></el-input>
         </el-form-item>
         <el-form-item label="评价">
           <el-input v-model="finishModel.evaluations" auto-complete="off" style="width:180%"></el-input>
         </el-form-item>
       </el-form>
       <div class="textC">
        <el-button type="primary" @click="finishSubmit">确定</el-button>
        <el-button type="primary" @click="finishDialogCancel">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "comPageSearch",
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
        orderStatus:"",
        pageNo: 0,
        pageSize: 0
      },
      dialog: {
        addModel: {
          show: false
        },
        editModel: {
          show: false
        },
        dispatchModel:{
          show: false
        },
        finishModel:{
          show: false
        }
      },
      addModel: {
          createUser:'',
          createDate:'',
          bookStartDate:'',
          customerName:'',
          mobileNo:"",
          staffCount:'',
          workHours:'',
          customerAddress:'',
          remark:'',
          itemsName: [],
          origin:'',
          amount:''
      },
      dispatchModel:{
        id:0,
        staffIds:[]
      },
      finishModel:{
        orderId:0,
        incomeAmount:0,
        evaluations:'',
        status:1
      },
      orderRules:{
        bookStartDate:[
          {type: 'date', required: true, message:'请输入预约日期',trigger:'change'}
        ],
        customerName:[
          {required: true, message:'请输入客户名称',trigger:'blur'}
        ],
        mobileNo:[
          {required: true, message:'请输入联系电话',trigger:'blur'}
        ],
        customerAddress:[
          {required: true, message:'请输入客户联系地址',trigger:'blur'}
        ],
        staffCount:[
          {required: true, message:'请输入人数'},
          {type:'number',message:'人数必须是数字'}
        ],
        workHours:[
          {required: true, message:'请输入工作时长'},
          {type:'number',message:'工作时长必须是数字'}
        ]
      },
      originOpts: [],
      itemOpts: [],
      tableData: [
        {
          bookStartDate:"",
          orderNo:"",
          customerName: "",
          customerAddress : "",
          bookStartDate: "",
          detailInfo: "",
          statusName: "",
          staffsName:""
        }
      ],
      staffItems:[],
      dispatchOrderId:0,
      searchStaffData:{
        staffName:''
      },
      finishOrderItems:[
        {status:1,title:'成功'},
        {status:0,title:'失败'}
      ]
      // isEditShow:false,
      // isDisShow:false,
      // isCancelShow:false,
      // isResetShow:false,
    };
  },
  computed:{
    isEditShow(){
      return function(row){
        return row.status == 1;
      }
    },
    isDisShow(){
      return function(row){
        return row.status == 1
      }
    },
    isCancelShow(){
      return function(row){
        return row.status == 1
      }
    },
    isResetShow(){
      return function(row){
        return row.status == 6
      }
    }
  },
  created() {
    this.selectOrderList();
    this.loadServiceItems();
  },
  methods: {
    searchClick() {
      alert("ok");
    },
    clearAddModel(){
      return {
         addModel: {
          createDate:'',
          bookStartDate:'',
          customerName:'',
          mobileNo:"",
          staffCount:'',
          workHours:'',
          customerAddress:'',
          detailInfo:'',
          items: [],
          origin:'',
          amount:''
      }
      }
    },
    showAddModelView() {
      this.dialog.addModel.show = true;
      this.addModel = this.clearAddModel();
      this.selectOriginList();
    },
    showEditModelView(row) {
          this.dialog.editModel.show = true;
          this.selectOriginList();
          let _self = this;
          this.$ajax({
              method:'GET',
              url: this.GLOBAL.REQUEST_HOST + "order/info/"+row.id
          }).then(function(res){
              if (res.data.code == 2000){
                  _self.addModel = res.data.data
                  _self.addModel.items = res.data.data.items;
              }
          })
      },
      LeftTableShowRowClick(row) {//新增2019-12-02
          this.dialog.addModel.show = true;
      },
    showDispatchOrder(row){//分派订单
       this.dialog.dispatchModel.show = true;
      //  console.log(row.id)
      this.dispatchModel.id = row.id
      this.searchStaffList()
    },
    showFinishOrder(row){
      this.dialog.finishModel.show = true;
      this.finishModel.orderId = row.id
    },
    selectOrderList() {
      let _self = this;
      this.searchData.pageNo = this.pageData.pageNo
      this.searchData.pageSize = this.pageData.pageSize
      this.$ajax({
        method: "POST",
        url: this.GLOBAL.REQUEST_HOST + "order/notDisOrderPage",
        data: this.searchData
      }).then(function(res) {
        if (res.data.code == 2000) {
          _self.tableData = res.data.data.list;
          _self.pageData.totalCount = res.data.data.total;
        }
      });
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
    //查询员工
    searchStaffList(){
      let _self = this;
      this.$ajax({
        method:'POST',
        url: this.GLOBAL.REQUEST_HOST + "staff/list",
        data: this.searchStaffData
      }).then(function(res){
         if (res.data.code == 2000) {
          _self.staffItems = res.data.data
        }
      }).catch(e=>console.log(e))
    },
    addSubmit(formName) {
      let _self = this;
      this.$refs[formName].validate((valid)=>{
        if (valid){
          this.$ajax({
            method: "POST",
            url: this.GLOBAL.REQUEST_HOST + "order/add",
            data: this.addModel
          }).then(function(res) {
            if (res.data.code == 2000) {
              _self.selectOrderList();
            }
          });
          _self.dialog.addModel.show = false;
        }
      })
    },
    editSubmit(){
      let _self = this;
      this.$ajax({
        method: 'POST',
        url: this.GLOBAL.REQUEST_HOST + "order/modify",
        data: this.addModel
      }).then(function(res){
        if (res.data.code == 2000){
          _self.selectOrderList();
          _self.dialog.editModel.show = false;
        }
      }).catch(function(e){
        console.log(e)
      })
    },
    dispatchSubmit(){ //提交订单分派信息
      let _self = this;
      this.$ajax({
        method: 'POST',
        url:this.GLOBAL.REQUEST_HOST + "order/dispatch",
        data: this.dispatchModel
      }).then(function(res){
        if (res.data.code == 2000){
           _self.dialog.dispatchModel.show = false;
           _self.selectOrderList();
        }
      }).catch(e=>console.log(e))
    },
    resetOrder(row){
      let _self = this;
      let orderId = row.id;
      this.$ajax({
        method:'GET',
        url: this.GLOBAL.REQUEST_HOST + "order/resetOrder/"+orderId,
      }).then(function(res){
        if (res.data.code == 2000){
          _self.selectOrderList();
        }
      }).catch(e => console.log(e))
    },


    finishSubmit(){ //订单结束
      let _self = this;
      this.$ajax({
        method: 'POST',
        url: this.GLOBAL.REQUEST_HOST + "order/finish",
        data: this.finishModel
      }).then(function(res){
        if (res.data.code == 2000){
          _self.dialog.finishModel.show = false;
          _self.selectOrderList();
        }
      }).catch(e=>console.log(e))
    },
    cancelOrder(row){
      this.$confirm('确认取消该订单吗？').then(_=>{
        console.log(_);
        let orderId = row.id
        let _self = this;
        this.$ajax({
          method: 'GET',
          url: this.GLOBAL.REQUEST_HOST + "order/cancelOrder/"+ orderId,
        }).then(function(res){
          if (res.data.code == 2000){
            _self.selectOrderList();
          }
        }).catch(e=>console.log(e));
      }).catch(e=>console.log(e));
    },
    cancel() {
      this.dialog.addModel.show = false;
    },
    editCancel(){
      this.dialog.editModel.show = false;
    },
    staffDialogCancel(){
      this.dialog.dispatchModel.show = false;
    },
    finishDialogCancel(){
      this.dialog.finishModel.show = false;
    },
    handleSelectionChange(val) {
      this.addModel.items = val;
    },
    pageChange(val) {
      this.pageData.pageNo = val
      this.selectOrderList();
    },
    staffPageChange(val){
      this.staffPageData.pageNo = val;
    }
  }
};
</script>
<style created>
/* .bg-purple-dark {background: #99a9bf} */
</style>
