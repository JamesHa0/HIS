<template>
  <div>
<!--    整体布局-->
    <el-card>
<!--      整体页面分为一行的左右两部分-->
      <el-row>
<!--        左侧部分-->
        <el-col :span="8">
          <el-row justify="space-between">
            <label class="label1">患者选择</label><el-button type="primary" :icon="Refresh" @click="refresh()"></el-button>
          </el-row>

          <el-input
            v-model="searchkey"
            style="max-width: 400px"
            placeholder="请输入患者名称"
            class="input-with-select"
            @keyup.enter="search(searchkey)"
          >
            <template #append>
              <el-button :icon="Search" @click="search(searchkey)" />
            </template>
          </el-input>

<!--          标签页-->
          <el-tabs type="border-card" :key="tableKey">
            <el-tab-pane label="本人">
              <lebel class="label1">未诊患者：</lebel>
              <RegisterByType
                :msg="{id:doctorid,belong:'doc',state:'unvisit',orderid:0}"
                @selectone="handleChildSelected"
                ref="ChildSelect0Regist"
              />
              <lebel class="label1">已诊患者：</lebel>
              <RegisterByType
                :msg="{id:doctorid,belong:'doc',state:'visit',orderid:1}"
                @selectone="handleChildSelected"
                ref="ChildSelect1Regist"
              />
            </el-tab-pane>

            <el-tab-pane label="科室">
              <lebel class="label1">未诊患者：</lebel>
              <RegisterByType
                :msg="{id:deptid,belong:'dept',state:'unvisit',orderid:2}"
                @selectone="handleChildSelected"
                ref="ChildSelect2Regist"
              />
              <lebel class="label1">已诊患者：</lebel>
              <RegisterByType
                :msg="{id:deptid,belong:'dept',state:'visit',orderid:3}"
                @selectone="handleChildSelected"
                ref="ChildSelect3Regist"
              />
            </el-tab-pane>
          </el-tabs>
        </el-col>


<!--        右侧部分-->
        <el-col :span="16" >


<!--          标签页-->
          <el-tabs type="border-card">
<!--            病例首页-->
            <el-tab-pane label="病例首页">
              <case ref="caseMethods" />
            </el-tab-pane>

<!--            检查申请-->
            <el-tab-pane label="检查申请">
              <inspect ref="inspectMethods"/>
            </el-tab-pane>
          </el-tabs>
        </el-col>
      </el-row>
    </el-card>



  </div>
</template>

<script setup lang="ts">

import {Search,Refresh} from "@element-plus/icons-vue";
import RegisterByType from "@/views/menzhenyisheng/component/RegisterByType.vue";
import {ref} from 'vue'
import {useUserStore} from "@/store/modules/user";
import Case from "@/views/menzhenyisheng/component/Case.vue";
import Inspect from "@/views/menzhenyisheng/component/Inspect.vue";



let tableKey = ref(0);
let searchkey = ref();
let msg = ref({
  id: '',
  belong:'',
  state:''
})
const userStore = useUserStore();
let doctorid = userStore.userInfo.id;
let deptid = userStore.userInfo.deptid;

let chosenregister = ref({
  casenumber:'',
  id:'',
});

// 患者信息组件列表
const ChildSelect0Regist = ref();
const ChildSelect1Regist = ref();
const ChildSelect2Regist = ref();
const ChildSelect3Regist = ref();

const RegisterRefArrays = ref([ChildSelect0Regist,ChildSelect1Regist,ChildSelect2Regist,ChildSelect3Regist]);


//Case子组件方法
let caseMethods = ref(null);

// Inspect子组件方法
let inspectMethods = ref();


function handleChildSelected(value:any){

  // 清除其他子组件选中行状态
  RegisterClearSelExceptCurrent(value)

  // 查询历史病例
  CaseOnRegisterSelChange(value);


  // 将选中的患者信息传递给Inspect子组件
  setSelectedRegisterToInspect(value)

}

function RegisterClearSelExceptCurrent(info:any){
  for(let i = 0;i < RegisterRefArrays.value.length; i++){
    if(i != info.orderid){
      let childRef = RegisterRefArrays.value[i];
      childRef.value.clearSelect();
    }
  }
}

const CaseOnRegisterSelChange=(value:any)=>{
  if (caseMethods.value){
    caseMethods.value.onRegisterChange(value);
  }
}

const setSelectedRegisterToInspect = (info:any) =>{
  if (inspectMethods.value){
    inspectMethods.value.refreshCurrentSelRegister(info);
  }
}


function refresh(){
  tableKey.value = Date.now();
}


// 模糊搜索
function search (searchkey: string) {
  for(let i = 0;i < RegisterRefArrays.value.length; i++){
    let childRef = RegisterRefArrays.value[i];
    if(childRef.value){
      childRef.value.search(searchkey);
    }
  }
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
:deep(.form1 .el-textarea__inner) {
  background-color: #edf5fd;
}

</style>
