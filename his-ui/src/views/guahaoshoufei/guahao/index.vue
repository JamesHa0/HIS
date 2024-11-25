<template>
  <el-card class="card1">
    <el-row>
      <el-col :span="8">
        <el-input
          v-model="searchkey"
          style="max-width: 400px"
          placeholder="请输入患者姓名"
          class="input-with-select"
        >
          <template #append>
            <el-button :icon="Search" />
          </template>
        </el-input>
      </el-col>

      <el-col :span="6">
        <el-button type="primary" :icon="Edit" @click="addFormVisible = true">挂号</el-button>
      </el-col>
    </el-row>
  </el-card>

  <el-card>
    <el-table :data="list" border style="width: 100%">
      <el-table-column prop="" label="病历号" width="150" />
      <el-table-column prop="" label="姓名" width="180" />
      <el-table-column prop="" label="性别" width="180" />
      <el-table-column prop="" label="身份证号" width="180" />
      <el-table-column prop="" label="出生日期" width="180" />
      <el-table-column prop="" label="年龄" width="180" />

      <el-table-column fixed="right" label="操作" min-width="120">

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
        <el-button type="primary" @click="handleAddFormClosed()">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>
  <!--添加挂号信息的对话框  END-->
</template>

<script setup lang="ts">

import {Edit, Search} from "@element-plus/icons-vue";
import ConstantItemAPI from "@/api/system/constantitem";


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

function handleAddFormClosed(){}



//患者列表
let list = ref([]);


onMounted(()=>{
  let param = {
    id:7 //7对应性别的字典组
  }
  ConstantItemAPI.getGender(param)
    .then(
      (data:any)=>{
        genders.value = data
      }

    )
})
</script>

<style scoped>

</style>
