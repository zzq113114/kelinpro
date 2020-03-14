<template>
  <div class="sys-page">
    <div class="page-content">
      <!-- <app-search> -->
      <el-form :inline="true" ref="searchform" :model="searchData" label-width="100px">                
        <el-form-item label="员工姓名">
          <el-col :span="10">
           <el-input v-model="searchData.staffName"  style="width:200%"></el-input>
          </el-col>
        </el-form-item>        
        <el-form-item>
          <div class="grid-content bg-purple-dark">
            <el-button type="primary" @click="selectStaffList">查询</el-button>
          </div>
        </el-form-item>
      </el-form>
      <!-- </app-search> -->
      <el-row>
        <el-col :span="24">
          <div class="grid-content bg-purple-dark">
            <el-button type="primary" @click="showAddModelView">增加员工</el-button>
          </div>
        </el-col>
      </el-row>
      <app-section>
        <el-table :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="staffName" label="员工姓名"></el-table-column>
          <el-table-column prop="departmentName" label="部门名称"></el-table-column>
          <el-table-column prop="title" label="职务"></el-table-column>
          <el-table-column  label="操作">
            <template slot-scope="scope">
              <el-button size="small" type="primary" @click="showEditModelView(scope.row)">修改</el-button>
              <el-button size="small" type="primary" @click="staffDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination background layout="total,prev, pager, next" @current-change="pageChange"  
        :total="pageData.totalCount" :page-size="pageData.pageSize"> 
        </el-pagination>
      </app-section>
    </div>
    <el-dialog title="新增员工" :visible.sync="dialog.addModel.show" :modal-append-to-body="false" custom-class="editPawDialog">
      <el-form :model="addModel" :rules="staffRules" ref="addModel" label-width="200px">       
        <el-form-item label="员工姓名" prop="staffName">
          <el-input v-model="addModel.staffName" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="职务" prop="title">
          <el-input v-model="addModel.title" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="部门" prop="staffCount">
          <el-input v-model="addModel.departmentName" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>        
      </el-form>
      <div class="textC">
        <el-button type="primary" @click="addSubmit('addModel')">保存</el-button>
        <el-button type="primary" @click="cancel">取消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="员工修改" :visible.sync="dialog.editModel.show" :modal-append-to-body="false" custom-class="editPawDialog">
      <el-form :model="addModel" :rules="staffRules" ref="addModel" label-width="200px">       
        <el-form-item label="员工姓名" prop="staffName">
          <el-input v-model="addModel.staffName" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="职务" prop="title">
          <el-input v-model="addModel.title" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="部门" prop="staffCount">
          <el-input v-model="addModel.departmentName" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>        
      </el-form>
      <div class="textC">
        <el-button type="primary" @click="editSubmit('addModel')">保存</el-button>
        <el-button type="primary" @click="editCancel">取消</el-button>
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
        staffName: "", 
        pageNo: 0,
        pageSize: 0
      },
      dialog: {
        addModel: {
          show: false
        },
        editModel: {
          show: false
        }
      },
      addModel: {
          staffName:'',
          title:'',
          departmentName:''       
      },
      staffRules:{
        staffName:[
          {required: true, message:'请输入员工姓名',trigger:'blur'}
        ],
        title:[
          {required: true, message:'请输员工职务',trigger:'blur'}
        ],
        departmentName:[
          {required: true, message:'请输入员工部门',trigger:'blur'}
        ]
      },      
      tableData: [
        {
          staffName: "",
          departmentName : "",
          title: ""
        }
      ]
    };
  },
  created() {
    this.selectStaffList();
    
  },
  methods: {
    showAddModelView() {
      this.dialog.addModel.show = true;
    },
    showEditModelView(row) {
      this.dialog.editModel.show = true;
      let _self = this;
      this.$ajax({
        method:'GET',
        url: this.GLOBAL.REQUEST_HOST + "staff/info/"+row.id
      }).then(function(res){
        if (res.data.code == 2000){
          _self.addModel = res.data.data
          _self.addModel.items = res.data.data.items;
        }
      })
    },
    selectStaffList() {
      let _self = this;
      this.searchData.pageNo = this.pageData.pageNo
      this.searchData.pageSize = this.pageData.pageSize
      this.$ajax({
        method: "POST",
        url: this.GLOBAL.REQUEST_HOST + "staff/page",
        data: this.searchData
      }).then(function(res) {
        if (res.data.code == 2000) {
          _self.tableData = res.data.data.list;
          _self.pageData.totalCount = res.data.data.total;
        }
      });
    },
    addSubmit(formName) {
      let _self = this;
      this.$refs[formName].validate((valid)=>{
        if (valid){
          this.$ajax({
            method: "POST",
            url: this.GLOBAL.REQUEST_HOST + "staff/add",
            data: this.addModel
          }).then(function(res) {
            if (res.data.code == 2000) {
              _self.selectStaffList()
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
        url: this.GLOBAL.REQUEST_HOST + "staff/modify",
        data: this.addModel
      }).then(function(res){
        if (res.data.code == 2000){
          _self.selectStaffList();
          _self.dialog.editModel.show = false;
        }
      }).catch(function(e){
        console.log(e)
      })
    },
    staffDelete(row){
      this.$confirm('确认删除该员工？')
        .then(()=> {
          let _self = this;
          this.$ajax({
            method: 'GET',
            url: this.GLOBAL.REQUEST_HOST + "staff/delete/"+row.id,
            data: this.addModel
            }).then(function(res){
              if (res.data.code == 2000){
                 _self.selectStaffList();
               }
              }).catch(function(e){
                 console.log(e)
              })
        }).catch( _ => {console.log(_)});
    },
    dispatchOrder(row){//分派订单
      console.log(row)
    },
    cancel() {
      this.dialog.addModel.show = false;
    },
    editCancel(){
      this.dialog.editModel.show = false;
    },
    handleSelectionChange(val) {
      this.addModel.items = val;
    },
    pageChange(val) {
      this.pageData.pageNo = val
      this.selectStaffList();
    }
  }
};
</script>
<style created>
/* .bg-purple-dark {background: #99a9bf} */
</style>
