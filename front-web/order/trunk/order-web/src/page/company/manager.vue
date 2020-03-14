<template>
  <div class="sys-page">
    <div class="page-content">
      <!-- <app-search> -->
      <el-form :inline="true" ref="searchform" :model="searchData" label-width="100px">
        <el-form-item label="公司名称">
          <el-input v-model="searchData.companyName"></el-input>
        </el-form-item>
        <el-form-item label="联系人名称">
          <el-input v-model="searchData.contact"></el-input>
        </el-form-item>
        <el-form-item label="创建时间">
          <el-col :span="11">
            <el-date-picker
              type="date"
              placeholder="选择日期"
              v-model="searchData.startCreateDate"
              style="width: 100%;"
            ></el-date-picker>
          </el-col>
          <el-col class="line" :span="1">--</el-col>
          <el-col :span="11">
            <el-date-picker
              type="date"
              placeholder="选择日期"
              v-model="searchData.endCreateDate"
              style="width: 100%;"
            ></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item>
          <div class="grid-content bg-purple-dark">
            <el-button type="primary" @click="selectCompanyList">查询</el-button>
          </div>
        </el-form-item>
      </el-form>
      <!-- </app-search> -->
      <el-row>
        <el-col :span="24">
          <div class="grid-content bg-purple-dark">
            <el-button type="primary" @click="showAddModelView">增加公司信息</el-button>
          </div>
        </el-col>
      </el-row>
      <app-section>
        <el-table :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="companyName" label="公司名称"></el-table-column>
          <el-table-column prop="contact" label="联系人"></el-table-column>
          <el-table-column prop="contactTel" label="联系电话"></el-table-column>
          <el-table-column prop="address" label="公司地址"></el-table-column>
          <el-table-column  label="操作">
            <template slot-scope="scope">
              <el-button size="small" type="primary" @click="showEditModelView(scope.row)">修改</el-button>
              <el-button size="small" type="danger" @click="deleteCompany(scope.row)">删除</el-button>
              <el-button size="small" type="success" @click="showConfigModelView(scope.row)">配置</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination background layout="total,prev, pager, next" @current-change="pageChange"  
        :total="pageData.totalCount" :page-size="pageData.pageSize"> 
        </el-pagination>
      </app-section>
    </div>
    <el-dialog
      title="新增公司信息" :visible.sync="dialog.addModel.show" :modal-append-to-body="false" custom-class="editPawDialog">
      <el-form :model="addModel" :rules="companyRules" ref="addModel" label-width="200px">
        <el-form-item label="家政公司名称" prop="companyName">
          <el-input v-model="addModel.companyName" auto-complete="off" style="width:70%"></el-input>
        </el-form-item>
        <el-form-item label="服务范围" prop="items" >
          <el-select v-model="addModel.items" multiple placeholder="请选择">
            <el-option v-for="item in itemOpts" :key="item.id" :label="item.itemName" :value="item.id" style="width:50%"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="统一社会信用给代码" prop="cuscc">
          <el-input v-model="addModel.cuscc" auto-complete="off" style="width:70%"></el-input>
        </el-form-item>
        <el-form-item label="联系人" prop="contact">
          <el-input v-model="addModel.contact" auto-complete="off" style="width:70%"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="contactTel">
          <el-input v-model="addModel.contactTel" auto-complete="off" style="width:70%"></el-input>
        </el-form-item>
        <el-form-item label="公司地址" prop="address">
          <el-input v-model="addModel.address" auto-complete="off" style="width:95%"></el-input>
        </el-form-item>
      </el-form>
      <div class="textC">
        <el-button type="primary" @click="addSubmit">保存</el-button>
        <el-button type="primary" @click="cancel">取消</el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="修改公司信息"
      :visible.sync="dialog.editModel.show"
      :modal-append-to-body="false"
      custom-class="editPawDialog">
      <el-form :model="addModel" ref="addModel" label-width="200px">
        <el-form-item label="家政公司名称" prop="companyName">
          <el-input v-model="addModel.companyName" auto-complete="off" style="width:70%"></el-input>
        </el-form-item>
        <el-form-item label="服务范围" prop="items" >
          <el-select v-model="addModel.items" multiple placeholder="请选择">
            <el-option v-for="item in itemOpts" :key="item.id" :label="item.itemName" :value="item.id" style="width:50%"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="统一社会信用给代码" prop="cuscc">
          <el-input v-model="addModel.cuscc" auto-complete="off" style="width:70%"></el-input>
        </el-form-item>
        <el-form-item label="联系人" prop="contact">
          <el-input v-model="addModel.contact" auto-complete="off" style="width:70%"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="contactTel">
          <el-input v-model="addModel.contactTel" auto-complete="off" style="width:70%"></el-input>
        </el-form-item>
        <el-form-item label="公司地址" prop="address">
          <el-input v-model="addModel.address" auto-complete="off" style="width:95%"></el-input>
        </el-form-item>
      </el-form>
      <div class="textC">
        <el-button type="primary" @click="editSubmit">保存</el-button>
        <el-button type="primary" @click="editCancel">取消</el-button>
      </div>
    </el-dialog>
    <!-- 家政服务公司服务信息配置弹框 -->
    <el-dialog
      title="公司信息配置" :visible.sync="dialog.configModel.show" custom-class="configDialog"  :modal-append-to-body="false"   style="width:140%" >
        <el-table :data="companyItemConfigTableData" >
          <el-table-column prop="itemName" label="服务项目"></el-table-column>
          <el-table-column prop="serviceItemConfigVo.bookCount" label="起订">
            <template slot-scope="scope">
              <el-input size="small" style="width:60px;" v-model="companyItemConfigTableData[scope.$index].serviceItemConfigVo.bookCount"></el-input>
            </template>
          </el-table-column> 
          <el-table-column prop="serviceItemConfigVo.unitPrice" label="单价"> 
            <template slot-scope="scope">
              <el-input size="small" style="width:60px;" v-model="companyItemConfigTableData[scope.$index].serviceItemConfigVo.unitPrice"></el-input>
            </template>           
          </el-table-column>
          <el-table-column prop="serviceItemConfigVo.unit" label="单位">
            <template slot-scope="scope">
              <el-input size="small" style="width:60px;" v-model="companyItemConfigTableData[scope.$index].serviceItemConfigVo.unit"></el-input>
            </template>          
          </el-table-column>
          <el-table-column prop="serviceItemConfigVo.salePrice" label="售价">            
            <template slot-scope="scope">
              <el-input size="small" style="width:60px;" v-model="companyItemConfigTableData[scope.$index].serviceItemConfigVo.salePrice"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="serviceItemConfigVo.totalTime" label="总时长">  
            <template slot-scope="scope">
              <el-input size="small" style="width:60px;" v-model="companyItemConfigTableData[scope.$index].serviceItemConfigVo.totalTime"></el-input>
            </template>          
          </el-table-column>
          <el-table-column prop="serviceItemConfigVo.pCount" label="总人数">            
            <template slot-scope="scope">
              <el-input size="small" style="width:60px;" v-model="companyItemConfigTableData[scope.$index].serviceItemConfigVo.pCount"  ></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="serviceItemConfigVo.timeRangeStart" label="时长区间">   
            <template slot-scope="scope">
              <el-input size="small" style="width:40px;" v-model="companyItemConfigTableData[scope.$index].serviceItemConfigVo.timeRangeStart"></el-input>
              -
              <el-input size="small" style="width:40px;" v-model="companyItemConfigTableData[scope.$index].serviceItemConfigVo.timeRangeEnd"></el-input>
            </template>         
          </el-table-column>
          <el-table-column prop="serviceItemConfigVo.priceRangeStart" label="价格区间">            
            <template slot-scope="scope">
              <el-input size="small" style="width:40px;" v-model="companyItemConfigTableData[scope.$index].serviceItemConfigVo.priceRangeStart"></el-input>
              -
              <el-input size="small" style="width:40px;" v-model="companyItemConfigTableData[scope.$index].serviceItemConfigVo.priceRangeEnd"></el-input>
            </template>
          </el-table-column>
          <el-table-column label="操作">            
            <template slot-scope="scope">
              <el-button type="primary" @click="configSubmit(scope)">保存</el-button>
            </template>
          </el-table-column>
        </el-table>  
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
      searchData: {
        companyName: "",
        contact: "",
        startCreateDate: "",
        endCreateDate: "",
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
        configModel: {
          show: false
        }
      },
      addModel: {
        items: [],
        companyName: "",
        cuscc: "",
        status: 0,
        contact: "",
        contactTel: "",
        address: ""
      },
      companyRules:{
        companyName:[
          {required: true, message:'请输入公司名称',trigger:'blur'}
        ],
        contactTel:[
          {required: true, message:'请输入联系电话',trigger:'blur'}
        ]
      },
      itemOpts: [],
      tableData: [
        {
          name: "",
          companyName: "",
          contact: "-",
          contactTel: "-",
          address: "-"
        }
      ],
      companyItemConfigTableData:[
        {
          companyId:"",
          itemId:"",
          itemName:"",
          serviceItemConfigVo:{
            id:"",
            bookCount:"",
            unitPrice:"",
            unit:"",
            salePrice:"",
            totalTime:"",
            pCount:"",
            timeRangeStart:"",
            timeRangeEnd:"",
            priceRangeStart:"",
            priceRangeEnd:""  
          }
        }
      ],
    };
  },
  created() {
    this.loadServiceItems();
    this.selectCompanyList();
  },
  methods: {
    showAddModelView() {
      this.dialog.addModel.show = true;
    },
    showEditModelView(row) {
      this.dialog.editModel.show = true;
      let _self = this;
      // console.log(row.id);
      this.companyItemConfigTableData.companyId = row.id;
      this.$ajax({
        method:'GET',
        url: this.GLOBAL.REQUEST_HOST + "company/info/"+row.id
      }).then(function(res){
        if (res.data.code == 2000){
          _self.addModel = res.data.data
          _self.addModel.items = res.data.data.items;
        }
      })
    },
    showConfigModelView(row){
      this.dialog.configModel.show = true;      
      let _self = this;
      this.$ajax({
        method: 'GET',
        url: this.GLOBAL.REQUEST_HOST + "company/itemConfig/"+row.id
      }).then(function(res){
        if (res.data.code == 2000){
          _self.companyItemConfigTableData = res.data.data;
        }
      }).catch(e=>console.log(e))
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
    selectCompanyList() {
      let _self = this;
      this.searchData.pageNo = this.pageData.pageNo
      this.searchData.pageSize = this.pageData.pageSize
      this.$ajax({
        method: "POST",
        url: this.GLOBAL.REQUEST_HOST + "company/page",
        data: this.searchData
      }).then(function(res) {
        if (res.data.code == 2000) {
          _self.tableData = res.data.data.list;
          _self.pageData.totalCount = res.data.data.total;
        }
      });
    },
    addSubmit() {
      let _self = this;
      this.$ajax({
        method: "POST",
        url: this.GLOBAL.REQUEST_HOST + "company/add",
        data: this.addModel
      }).then(function(res) {
        if (res.data.code == 2000) {
          _self.selectCompanyList();
        }
      });
      this.dialog.addModel.show = false;
    },
    editSubmit(){
      let _self = this;
      this.$ajax({
        method: 'POST',
        url: this.GLOBAL.REQUEST_HOST + "company/modify",
        data: this.addModel
      }).then(function(res){
        if (res.data.code == 2000){
          _self.selectCompanyList();
          _self.dialog.editModel.show = false;
        }
      }).catch(function(e){
        console.log(e)
      })
    },
    configSubmit(scope){
      let index = scope.$index;
      let _self = this;
      //  console.log(index,this.companyItemConfigTableData[index].serviceItemConfigVo)

      let saveData =  this.companyItemConfigTableData[index].serviceItemConfigVo;
      saveData.companyId = this.companyItemConfigTableData[index].companyId;
      saveData.itemId = this.companyItemConfigTableData[index].itemId;
      // console.log(saveData)
      this.$ajax({
        method:'POST',
        url:this.GLOBAL.REQUEST_HOST + 'company/itemConfig/save',
        data: saveData
      }).then(function(res){
        if(res.data.code== 2000){
            _self.dialog.configModel.show = false;
        }
      }).catch(e => console.log(e))
    },
    deleteCompany(row){
      this.$confirm('确认删除？')
          .then(_ => {
            console.log(_)
            let _self = this;
            this.$ajax({
              method: 'GET',
              url: this.GLOBAL.REQUEST_HOST + "company/delete/"+row.id,
            }).then(function(res){
              if (res.code == 2000){
                _self.selectCompanyList();
              }
            })
          })
          .catch( _ => {console.log(_)});
    },
    cancel() {
      this.dialog.addModel.show = false;
    },
    editCancel(){
      this.dialog.editModel.show = false;
    },
    configCancel(){
      this.dialog.configModel.show = false;
    },
    handleSelectionChange(val) {
      this.addModel.items = val;
    },
    pageChange(val) {
      this.pageData.pageNo = val
      this.selectCompanyList();
    }
  }
};
</script>
<style created>
  .configDialog {
    left: 35%;
  }
</style>
