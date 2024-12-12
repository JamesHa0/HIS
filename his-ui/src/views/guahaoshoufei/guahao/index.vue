<template>
  <div>
  <el-card class="card1">
    <el-row>
      <el-col :span="8">
        <el-input
          v-model="searchkey"
          style="max-width: 400px"
          placeholder="请输入患者姓名"
          class="input-with-select"
          @keyup.enter="search(searchkey)"
        >
          <template #append>
            <el-button :icon="Search" @click="search(searchkey)"/>
          </template>
        </el-input>
      </el-col>

      <el-col :span="6">
        <el-button type="primary" :icon="Edit" @click="addFormVisible = true">挂号</el-button>
      </el-col>
    </el-row>
  </el-card>

  <el-card>
    <el-table :data="list" border style="width: 100%" :key="tableKey">
      <el-table-column prop="casenumber" label="病历号" min-width="25%" />
      <el-table-column prop="realname" label="姓名" min-width="20%" />
      <el-table-column prop="gender" label="性别" min-width="10%" />
      <el-table-column prop="idnumber" label="身份证号" min-width="25%" />
      <el-table-column prop="age" label="年龄" min-width="10%" />

      <el-table-column fixed="right" label="操作" min-width="10%">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="delRegist(scope.row)">退号</el-button>
        </template>

      </el-table-column>
    </el-table>
    <el-pagination
      v-if="total > 0"
      v-model:current-page="currentPage"
      :page-size="pageSize"
      :total="total"
      layout="total, prev, pager, next, jumper"
      @current-change="handleCurrentChange"
    />
  </el-card>




  <!--添加挂号信息的对话框  BEGIN-->
  <el-dialog v-model="addFormVisible" title="挂号信息" width="500" >
    <el-form :model="addform">
      <el-form-item label="病历号" :label-width="100">
        <el-input v-model="addform.casenumber" />
      </el-form-item>
      <el-form-item label="真实姓名" :label-width="100">
        <el-input v-model="addform.realname" />
      </el-form-item>
      <el-form-item label="性别" :label-width="100">
        <el-select v-model="addform.gender">
          <el-option v-for="item in genders"
                     :key="item.id"
                     :label="item.constantname"
                     :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="身份证号" :label-width="100">
        <el-input v-model="addform.idnumber" />
      </el-form-item>
      <el-form-item label="年龄" :label-width="100">
        <el-input v-model="addform.age" />
      </el-form-item>

      <el-form-item label="家庭住址" :label-width="100">
        <el-input v-model="addform.homeaddress" />
      </el-form-item>

    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="addFormVisible = false">取消</el-button>
        <el-button type="primary" @click="add_register()">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>
  <!--添加挂号信息的对话框  END-->
  </div>
</template>

<script setup lang="ts">

import {Edit, Search} from "@element-plus/icons-vue";
import ConstantItemAPI from "@/api/system/constantitem";
import GuahaoAPI from "@/api/guahaoshoufei/guahao";
import {useUserStore} from "@/store";

let tableKey = ref(0);
let searchkey = ref();
let addFormVisible = ref(false);
let addform = reactive({
  casenumber:'',
  realname:'',
  gender:'',
  idnumber:'',
  age:'',
  agetype:'',
  homeaddress:''
})

let genders = ref([
  {
    id:null,
    constantname:'',
  }
])

// 当前用户信息
const userStore = useUserStore();

//挂号列表
let list = ref([]);

// 当前页码，初始化为1
let currentPage = ref(1);
// 每页显示的记录数量，可根据实际需求调整，这里假设每页显示10条记录
let pageSize = ref(10);
// 总记录数，初始为0，后续会从后端获取并更新
let total = ref(0);

// 页码改变时的处理方法
const handleCurrentChange = (newPage: number) => {
  currentPage.value = newPage;
  if(searchkey.value == "" || searchkey.value == null || searchkey.value === undefined){
    get_list();
  }else {
    console.log("searchkey:",searchkey.value);
    let params = {
      page: currentPage.value,
      size: pageSize.value,
      searchkey: searchkey.value
    }
    GuahaoAPI.search(params)
      .then(
        (data:any) => {
          console.log(data)
          list.value = data.list;
          total.value = data.total;
          tableKey.value = Date.now();
        }
      )
  }
};

//模糊搜索
function search (searchkey: string){
  console.log(searchkey)
  let params = {
    page: currentPage.value,
    size: pageSize.value,
    searchkey: searchkey
  }
  GuahaoAPI.search(params)
    .then(
      (data:any) => {
        console.log(data)
        list.value = data.list;
        total.value = data.total;
        currentPage.value = 1;
        tableKey.value = Date.now();
      }
    )
}

//新增挂号
function add_register(){
  let params = addform;
  params.userid = userStore.userInfo.id;
  params.deptid = userStore.userInfo.deptid;
  GuahaoAPI.add_register(params)
    .then(
      (data) => {
        alert(data);
        addFormVisible.value = false;
        get_list();
      }
    )
}

//获取性别常数项
function get_gender(){
  let param = {
    id:7 //7对应性别的字典组
  }
  ConstantItemAPI.getGender(param)
    .then(
      (data:any)=>{
        genders.value = data
      }
    )
}

// 退号按钮
function delRegist(row:any){
  ElMessageBox.confirm("确认退号?", "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  }).then(function (){
    console.log(row.id)
    let params={
      regist_id:row.id,
      state:"已退费"
    }
    GuahaoAPI.change_state(params)
      .then(
        (data:any) => {
          alert(data)
          //刷新页面
          get_list();
        })
  })
}




// 获取列表
function get_list() {
  console.log("currentPage.value:",currentPage.value);
  console.log("pageSize.value:",pageSize.value)
  let params = {
    page: currentPage.value,
    size: pageSize.value
  }
  GuahaoAPI.getall(params)
    .then(
      (data: any) => {
        list.value = data.list;
        total.value = data.total;
        tableKey.value = Date.now();
      }
    );
}

onMounted(()=>{
  get_list();
  get_gender();
})
</script>

<style scoped>

</style>
