<template>
  <div class="sys-page">
    <div class="page-content">
      <!-- <app-search> -->
      <el-form :inline="true" ref="searchform" :model="searchData" label-width="100px">                
        <el-form-item label="账号">
          <el-col :span="10">
           <el-input v-model="searchData.userName"  style="width:200%"></el-input>
          </el-col>
        </el-form-item>        
        <el-form-item>
          <div class="grid-content bg-purple-dark">
            <el-button type="primary" @click="selectUserList">查询</el-button>
          </div>
        </el-form-item>
      </el-form>
      <!-- </app-search> -->
      <el-row>
        <el-col :span="24">
          <div class="grid-content bg-purple-dark">
            <el-button type="primary" @click="showAddModelView">增加账号</el-button>
          </div>
        </el-col>
      </el-row>
      <app-section>
        <el-table :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="userName" label="账号姓名"></el-table-column>
          <el-table-column prop="userAccount" label="登录账号"></el-table-column>
          <el-table-column  label="操作">
            <template slot-scope="scope">
              <el-button size="small" type="primary" @click="showEditModelView(scope.row)">修改</el-button>
              <el-button size="small" type="primary" @click="userDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination background layout="total,prev, pager, next" @current-change="pageChange"  
        :total="pageData.totalCount" :page-size="pageData.pageSize"> 
        </el-pagination>
      </app-section>
    </div>
    <el-dialog title="新增账号" :visible.sync="dialog.addModel.show" :modal-append-to-body="false" custom-class="editPawDialog">
      <el-form :model="addModel" :rules="userRules" ref="addModel" label-width="200px">       
        <el-form-item label="账号姓名" prop="userName">
          <el-input v-model="addModel.userName" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="登录账号" prop="userAccount">
          <el-input v-model="addModel.userAccount" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="登录密码" prop="password">
          <el-input v-model="addModel.password" type="password" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>        
        <el-form-item label="所属角色" prop="roleId">
          <el-select v-model="addModel.roleId" placeholder="请选择" style="width:50%">
            <el-option v-for="item in userRoles" :key="item.id" :label="item.roleName" :value="item.id" style="width:100%"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div class="textC">
        <el-button type="primary" @click="addSubmit('addModel')">保存</el-button>
        <el-button type="primary" @click="cancel">取消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="员工修改" :visible.sync="dialog.editModel.show" :modal-append-to-body="false" custom-class="editPawDialog">
      <el-form :model="addModel" :rules="userRules"  label-width="200px">       
        <el-form-item label="账号姓名" prop="userName">
          <el-input v-model="addModel.userName" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="登录账号" prop="userAccount">
          <el-input v-model="addModel.userAccount" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="登录密码" prop="password">
          <el-input v-model="addModel.password" type="password" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>        
        <el-form-item label="所属角色" prop="roleId">
          <el-select v-model="addModel.roleId" placeholder="请选择" style="width:50%">
            <el-option v-for="item in userRoles" :key="item.id" :label="item.roleName" :value="item.id" ></el-option>
          </el-select>
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
        userName: "", 
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
      userRoles:[
      ],
      addModel: {
          userName:'',
          userAccount:'',
          password:'',
          roleId:0,
      },
      userRules:{
        userName:[
          {required: true, message:'请输入账号姓名',trigger:'blur'}
        ],
        userAccount:[
          {required: true, message:'请输入登录账号',trigger:'blur'}
        ],
        password:[
          {required: true, message:'请输入登录密码',trigger:'blur'}
        ]
      },  
      tableData: [
        {
          userName: "",
          userAccount : ""
        }
      ]
    };
  },
  created() {
    this.selectUserList();    
  },
  methods: {
    showAddModelView() {
      this.addModel.userName = '',
      this.addModel.userAccount = '',
      this.addModel.password = '',
      this.addModel.roleId = ''
      this.dialog.addModel.show = true;
      this.selectRoleList()
    },
    showEditModelView(row) {
      this.dialog.editModel.show = true;
      this.selectRoleList();
      let _self = this;
      this.$ajax({
        method:'GET',
        url: this.GLOBAL.REQUEST_HOST + "user/info/"+row.id
      }).then(function(res){
        if (res.data.code == 2000){
          _self.addModel = res.data.data;
          _self.addModel.roleId = res.data.data.roleId+"";//默认值ID必须为String类型
        }
      })
    },
    selectUserList() {
      let _self = this;
      this.searchData.pageNo = this.pageData.pageNo
      this.searchData.pageSize = this.pageData.pageSize
      this.$ajax({
        method: "POST",
        url: this.GLOBAL.REQUEST_HOST + "user/page",
        data: this.searchData
      }).then(function(res) {
        if (res.data.code == 2000) {
          _self.tableData = res.data.data.list;
          _self.pageData.totalCount = res.data.data.total;
        }
      });
    },
    selectRoleList(){
      let _self = this;
      this.$ajax({
        method:'POST',
        url:this.GLOBAL.REQUEST_HOST+"role/list",
      }).then(function(res){
        if (res.data.code == 2000){
          _self.userRoles = res.data.data
        }
      }).catch(e=>console.log(e))
    },
    addSubmit(formName) {
      let _self = this;
      this.$refs[formName].validate((valid)=>{
        if (valid){
          this.$ajax({
            method: "POST",
            url: this.GLOBAL.REQUEST_HOST + "user/add",
            data: this.addModel
          }).then(function(res) {
            if (res.data.code == 2000) {
              _self.selectUserList()
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
        url: this.GLOBAL.REQUEST_HOST + "user/modify",
        data: this.addModel
      }).then(function(res){
        if (res.data.code == 2000){
          _self.selectUserList();
          _self.dialog.editModel.show = false;
        }
      }).catch(function(e){
        console.log(e)
      })
    },
    userDelete(row){
      this.$confirm('确认删除该账号？')
        .then(()=> {
          let _self = this;
          this.$ajax({
            method: 'GET',
            url: this.GLOBAL.REQUEST_HOST + "user/delete/"+row.id,
            data: this.addModel
            }).then(function(res){
              if (res.data.code == 2000){
                 _self.selectUserList();
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
      this.selectUserList();
    }
  }
};
</script>
<style created>
/* .bg-purple-dark {background: #99a9bf} */
</style>
