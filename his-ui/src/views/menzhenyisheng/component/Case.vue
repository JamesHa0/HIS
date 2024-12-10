<template>
  <div>
    <el-row justify="center">
      <el-col :span="6" style="background-color: #EAF1F5">
        <el-button type="primary" @click="update" :disabled="!updatedbuttonable" text><el-icon><Tickets /></el-icon>修改诊断</el-button>
      </el-col>
      <el-col :span="6" style="background-color: #EAF1F5" >
        <el-button type="primary" @click="save" :disabled="!savedbuttonable" text><el-icon><CircleCheckFilled /></el-icon>提交诊断</el-button>
      </el-col>
      <el-col :span="6" style="background-color: #EAF1F5">
        <el-button type="primary" @click="clearForm" text><el-icon><CircleCloseFilled /></el-icon>清空所有</el-button>
      </el-col>
      <el-col :span="6" style="background-color: #EAF1F5">
        <el-button type="primary" @click="refresh" text><el-icon><Refresh /></el-icon>刷新</el-button>
      </el-col>
    </el-row>


    <el-form class="form1" :model="recordform" :key="formKey">
      <lebel class="label1">病史内容：</lebel>
      <el-form-item label="主诉" :label-width="100">
        <el-input class="test" v-model="recordform.readme" type="textarea" />
      </el-form-item>

      <el-form-item label="现病史" :label-width="100">
        <el-input v-model="recordform.present" type="textarea" />
      </el-form-item>

      <el-form-item label="现病治疗情况" :label-width="100">
        <el-input v-model="recordform.presenttreat" type="textarea" />
      </el-form-item>

      <el-form-item label="既往史" :label-width="100">
        <el-input v-model="recordform.history" type="textarea" />
      </el-form-item>

      <el-form-item label="过敏史" :label-width="100">
        <el-input v-model="recordform.allergy" type="textarea" />
      </el-form-item>

      <el-form-item label="体格检查" :label-width="100">
        <el-input v-model="recordform.physique" type="textarea" />
      </el-form-item>

      <el-form-item label="检查建议" :label-width="100">
        <el-input v-model="recordform.proposal" type="textarea" />
      </el-form-item>

      <el-form-item label="检查结果" :label-width="100">
        <el-input v-model="recordform.checkresult" type="textarea" />
      </el-form-item>

      <lebel class="label1">评估/诊断：</lebel>

      <el-form-item label="诊断结果" :label-width="100">
        <el-input v-model="recordform.diagnosis" type="textarea" />
      </el-form-item>

      <el-form-item label="注意事项" :label-width="100">
        <el-input v-model="recordform.careful" type="textarea" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="update" :disabled="!updatedbuttonable" text><el-icon><Tickets /></el-icon>修改诊断</el-button>
        <el-button type="primary" @click="save" :disabled="!savedbuttonable" text><el-icon><CircleCheckFilled /></el-icon>提交诊断</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">

import {CircleCheckFilled, CircleCloseFilled, Refresh, Tickets} from "@element-plus/icons-vue";
import {ref} from "vue";
import MenzhenAPI from "@/api/menzhenyisheng/menzhen";

let formKey = ref(0);
let recordform = ref({
  registid:'',//挂号id
  readme:'',//主诉
  present:'',//现病史
  presenttreat:'',//现病治疗情况
  history:'',//既往史
  allergy:'',//过敏史
  physique:'',//体格检查
  checkresult:'',//检查结果
  diagnosis:'',//诊断结果
  proposal:'',//检查建议
  careful:'',//注意事项
  casenumber:'',//病历号
});

let savedbuttonable = ref(false)
let updatedbuttonable = ref(false)

let oneregist = reactive({})


// 操作前检查是否选中
function checkSelected(){
  let params = recordform;
  params.value.casenumber = oneregist.casenumber;
  params.value.registid= oneregist.id;

  console.log("操作的患者病历号：",params.value.casenumber);

  if(params.value.casenumber === undefined || params.value.casenumber === null || params.value.casenumber === ''){
    ElMessage.error('请先选择患者！');
    return null;
  } else {
    return params;
  }
}


function save(){
  let params = checkSelected();
  if (params === null){
     ElMessage.error('请先选择患者！');
  } else{
    MenzhenAPI.add_record(params.value).then(
      (data) => {
        alert(data);
      })
    // 修改患者状态信息为：未缴费
    MenzhenAPI.change_state({regist_id:params.value.registid,state:"未缴费"}).then(
      (data) => {
        alert(data);
      }
    )
  }
}


function update() {
  let params = checkSelected();
  if (params === null){
    ElMessage.error('请先选择患者！');
  } else{
    MenzhenAPI.update_by_casenumber(params.value).then(
      (data) => {
        alert(data);
      }
    )
  }
}

const onRegisterChange = (value:any) => {
  oneregist = value
  clearForm()

  let params = {
    regist_id:value.id
  }
  MenzhenAPI.get_one_by_regist(params).then(
    (data)=>{
      if(data != null && data != undefined){
        recordform.value = data
      } else{
        clearForm()
      }
      if(value.visitstate == 1){
        savedbuttonable.value = true
        updatedbuttonable.value = false
      } else{
        savedbuttonable.value = false
        updatedbuttonable.value = true
      }
    }
  )
}

function clearForm(){
  recordform.value.readme = ''
  recordform.value.present = ''
  recordform.value.presenttreat = ''
  recordform.value.history = ''
  recordform.value.allergy = ''
  recordform.value.physique = ''
  recordform.value.checkresult = ''
  recordform.value.casenumber = ''
  recordform.value.registid = ''
  recordform.value.diagnosis = ''
  recordform.value.proposal = ''
  recordform.value.careful = ''
}


defineExpose({
  onRegisterChange
})

function refresh(){
  onRegisterChange(oneregist);
}

</script>

<style scoped>
.label1 {
  color: #888888;
  width:100px;
  padding: 2px;
  border-radius: 5px;
  background-color: #e6f7ff;
  display: block;
  border: solid 1px #bfcbd9;
  margin-bottom:5px;
}
</style>
