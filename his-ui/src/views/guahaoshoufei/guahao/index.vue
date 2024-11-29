<template>
  <div>
  <el-card class="card1">
    <el-row>
      <el-col :span="8">
        <el-input
          v-model="search_name"
          style="max-width: 400px"
          placeholder="请输入患者姓名"
          class="input-with-select"
          @keyup.enter="search(search_name)"
        >
          <template #append>
            <el-button :icon="Search" @click="search(search_name)"/>
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
      <el-table-column prop="casenumber" label="病历号" width="150" />
      <el-table-column prop="realname" label="姓名" width="180" />
      <el-table-column prop="gender" label="性别" width="180" />
      <el-table-column prop="idnumber" label="身份证号" width="180" />
<!--      <el-table-column prop="" label="出生日期" v-model="CaseNumber" width="180" />-->
      <el-table-column prop="age" label="年龄" width="180" />

      <el-table-column fixed="right" label="操作" min-width="120">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="">
            编辑
          </el-button>
          <el-button link type="primary" size="small" @click="">删除</el-button>
        </template>

      </el-table-column>
    </el-table>
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
        <el-input v-model="addform.agetype" />
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

let tableKey = ref(0);
let search_name = ref();
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

//挂号列表
let list = ref([]);

//模糊搜索
function search (search_name: string){
  console.log(search_name)
  GuahaoAPI.search({search_name: search_name})
    .then(
      (data:any) => {
        console.log(data)
        list.value = data;
        tableKey.value = Date.now();
      }
    )
}

//新增挂号
function add_register(){
  console.log(addform);
  GuahaoAPI.add_register(addform)
    .then(
      (data) => {
        console.log(data);
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

//获取列表
function get_list(){
  GuahaoAPI.getall()
    .then(
      (data:any)=>{
        list.value = data;
        tableKey.value = Date.now();
      }
    )
}

onMounted(()=>{
  get_list();
  get_gender();
})
</script>

<style scoped>

</style>
