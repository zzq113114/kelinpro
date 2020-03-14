<template>
  <div class="sys-page">
    <div class="page-content">
      <!-- <app-search> -->
      <el-form :inline="true" ref="searchform" :model="searchData" label-width="100px">                
        <el-form-item label="角色名称">
          <el-col :span="10">
           <el-input v-model="searchData.staffName"  style="width:200%"></el-input>
          </el-col>
        </el-form-item>        
        <el-form-item>
          <div class="grid-content bg-purple-dark">
            <el-button type="primary" @click="selectRoleList">查询</el-button>
          </div>
        </el-form-item>
      </el-form>
      <!-- </app-search> -->
      <el-row>
        <el-col :span="24">
          <div class="grid-content bg-purple-dark">
            <el-button type="primary" @click="showAddModelView">增加角色</el-button>
          </div>
        </el-col>
      </el-row>
      <app-section>
        <el-table :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="roleName" label="角色姓名"></el-table-column>
          <el-table-column prop="funMenuStr" label="权限模块"></el-table-column>
          <el-table-column prop="roleDesc" label="角色描述"></el-table-column>
          <el-table-column  label="操作">
            <template slot-scope="scope">
              <el-button size="small" type="primary" @click="showEditModelView(scope.row)">修改</el-button>
              <el-button size="small" type="primary" @click="roleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination background layout="total,prev, pager, next" @current-change="pageChange"  
        :total="pageData.totalCount" :page-size="pageData.pageSize"> 
        </el-pagination>
      </app-section>
    </div>
    <el-dialog title="新增角色" :visible.sync="dialog.addModel.show" :modal-append-to-body="false" custom-class="editPawDialog">
      <el-form :model="addModel" :rules="roleRules" ref="addModel" label-width="200px">       
        <el-form-item label="角色姓名" prop="roleName">
          <el-input v-model="addModel.roleName" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="授权功能" prop="funMenuIds">
          <el-select v-model="addModel.funMenuIds" multiple placeholder="请选择">
            <el-option v-for="item in funMenus" :key="item.id" :label="item.menuName" :value="item.id" style="width:50%"></el-option>
          </el-select>
        </el-form-item> 
        <el-form-item label="角色描述" prop="roleDesc">
          <el-input type="textarea" v-model="addModel.roleDesc" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>      
      </el-form>
      <div class="textC">
        <el-button type="primary" @click="addSubmit('addModel')">保存</el-button>
        <el-button type="primary" @click="cancel">取消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="角色修改" :visible.sync="dialog.editModel.show" :modal-append-to-body="false" custom-class="editPawDialog">
      <el-form :model="addModel" :rules="roleRules" ref="addModel" label-width="200px">       
        <el-form-item label="角色姓名" prop="roleName">
          <el-input v-model="addModel.roleName" auto-complete="off" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="授权功能" prop="funMenuIds">
           <el-select v-model="addModel.funMenuIds" multiple placeholder="请选择">
            <el-option v-for="item in funMenus" :key="item.id" :label="item.menuName" :value="item.id" style="width:50%"></el-option>
          </el-select>
        </el-form-item>  
        <el-form-item label="角色描述" prop="roleDesc">
          <el-input type="textarea" v-model="addModel.roleDesc" auto-complete="off" style="width:30%"></el-input>
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
          roleName:'',
          funMenuIds:[],
          roleDesc:''       
      },
      roleRules:{
        roleName:[
          {required: true, message:'请输入角色名称',trigger:'blur'}
        ]
      },
      funMenus:[{
        id:'',
        menuCode :'',
        menuName:''
      }],    
      tableData: [
        {
          roleName: "",
          funMenuStr : "",
          roleDesc: ""
        }
      ]
    };
  },
  created() {
    this.selectRoleList();
    this.selectFunMentList();   
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
        url: this.GLOBAL.REQUEST_HOST + "role/info/"+row.id
      }).then(function(res){
        if (res.data.code == 2000){
          _self.addModel = res.data.data
          _self.addModel.funMenuIds =  res.data.data.funMenuIds;
        }
      })
    },
    selectRoleList() {
      let _self = this;
      this.searchData.pageNo = this.pageData.pageNo
      this.searchData.pageSize = this.pageData.pageSize
      this.$ajax({
        method: "POST",
        url: this.GLOBAL.REQUEST_HOST + "role/rolePage",
        data: this.searchData
      }).then(function(res) {
        if (res.data.code == 2000) {
          _self.tableData = res.data.data.list;
          _self.pageData.totalCount = res.data.data.total;
        }
      });
    },
    selectFunMentList(){
      let _self = this;
      this.$ajax({
        method: "POST",
        url: this.GLOBAL.REQUEST_HOST + "role/funMenuList"
      }).then(function(res) {
          if (res.data.code == 2000) {             
              _self.funMenus = res.data.data;
            }
      });
    },
    addSubmit(formName) {
      let _self = this;
      this.$refs[formName].validate((valid)=>{
        if (valid){
          this.$ajax({
            method: "POST",
            url: this.GLOBAL.REQUEST_HOST + "role/add",
            data: this.addModel
          }).then(function(res) {
            if (res.data.code == 2000) {
              _self.selectRoleList()
            }
          });
          _self.dialog.addModel.show = false;
        }
      })      
    },
    editSubmit(){
      let _self = this;
      console.log(this.addModel)
      this.$ajax({
        method: 'POST',
        url: this.GLOBAL.REQUEST_HOST + "role/modify",
        data: this.addModel
      }).then(function(res){
        if (res.data.code == 2000){
          _self.selectRoleList();
          _self.dialog.editModel.show = false;
        }
      }).catch(function(e){
        console.log(e)
      })
    },
    roleDelete(row){
      this.$confirm('确认删除该角色？')
        .then(()=> {
          let _self = this;
          this.$ajax({
            method: 'GET',
            url: this.GLOBAL.REQUEST_HOST + "role/delete/"+row.id,
            data: this.addModel
            }).then(function(res){
              if (res.data.code == 2000){
                 _self.selectRoleList();
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
      this.selectRoleList();
    }
  }
};
</script>
<style created>
/* .bg-purple-dark {background: #99a9bf} */
</style>
