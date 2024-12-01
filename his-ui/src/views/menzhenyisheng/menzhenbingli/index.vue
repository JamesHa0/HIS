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
          >
            <template #append>
              <el-button :icon="Search" />
            </template>
          </el-input>

<!--          标签页-->
          <el-tabs type="border-card" :key="tableKey">
            <el-tab-pane label="本人">
              <lebel class="label1">未诊患者：</lebel>
              <RegisterByType :msg="{id:doctorid,belong:'doc',state:'unvisit'}" @selectone="handleChildSelected"/>
              <lebel class="label1">已诊患者：</lebel>
              <RegisterByType :msg="{id:doctorid,belong:'doc',state:'visit'}" @selectone="handleChildSelected"/>
            </el-tab-pane>

            <el-tab-pane label="科室">
              <lebel class="label1">未诊患者：</lebel>
              <RegisterByType :msg="{id:deptid,belong:'dept',state:'unvisit'}" @selectone="handleChildSelected"/>
              <lebel class="label1">已诊患者：</lebel>
              <RegisterByType :msg="{id:deptid,belong:'dept',state:'visit'}" @selectone="handleChildSelected"/>
            </el-tab-pane>
          </el-tabs>
        </el-col>


<!--        右侧部分-->
        <el-col :span="16" >


<!--          标签页-->
          <el-tabs type="border-card">
<!--            病例首页-->
            <el-tab-pane label="病例首页">
              <el-row justify="center">
                <el-col :span="6" style="background-color: #EAF1F5">
                  <el-button type="primary" @click="" text><el-icon><Tickets /></el-icon>暂存</el-button>
                </el-col>
                <el-col :span="6" style="background-color: #EAF1F5" >
                  <el-button type="primary" @click="submit" text><el-icon><CircleCheckFilled /></el-icon>提交</el-button>
                </el-col>
                <el-col :span="6" style="background-color: #EAF1F5">
                  <el-button type="primary" @click="" text><el-icon><CircleCloseFilled /></el-icon>清空所有</el-button>
                </el-col>
                <el-col :span="6" style="background-color: #EAF1F5">
                  <el-button type="primary" @click="" text><el-icon><Refresh /></el-icon>刷新</el-button>
                </el-col>
              </el-row>


              <el-form class="form1" :model="record">
                <lebel class="label1">病史内容：</lebel>
                <el-form-item label="主诉" :label-width="100">
                  <el-input class="test" v-model="record.readme" type="textarea" />
                </el-form-item>

                <el-form-item label="现病史" :label-width="100">
                  <el-input v-model="record.present" type="textarea" />
                </el-form-item>

                <el-form-item label="现病治疗情况" :label-width="100">
                  <el-input v-model="record.presenttreat" type="textarea" />
                </el-form-item>

                <el-form-item label="既往史" :label-width="100">
                  <el-input v-model="record.history" type="textarea" />
                </el-form-item>

                <el-form-item label="过敏史" :label-width="100">
                  <el-input v-model="record.allergy" type="textarea" />
                </el-form-item>

                <el-form-item label="体格检查" :label-width="100">
                  <el-input v-model="record.physique" type="textarea" />
                </el-form-item>

                <lebel class="label1">评估/诊断：</lebel>
                <el-card>
                  <template #header>
                    <div class="card-header">
                      <span>西医诊断</span>
                    </div>
                  </template>
                  //todo

                </el-card>

                <el-card>
                  <template #header>
                    <div class="card-header">
                      <span>中医诊断</span>
                    </div>
                  </template>
                  //todo

                </el-card>

                <el-form-item label="检查建议" :label-width="100">
                  <el-input v-model="record.proposal" type="textarea" />
                </el-form-item>

                <el-form-item label="注意事项" :label-width="100">
                  <el-input v-model="record.careful" type="textarea" />
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" @click="" text><el-icon><CircleCheckFilled /></el-icon>提交</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>

<!--            检查申请-->
            <el-tab-pane label="检查申请">
              <el-row justify="center">
                <el-col :span="6" style="background-color: #EAF1F5">
                  <el-button type="primary" @click="" text><el-icon><Tickets /></el-icon>修改</el-button>
                </el-col>
                <el-col :span="6" style="background-color: #EAF1F5">
                  <el-button type="primary" @click="showinspcetitem" text><el-icon><CircleCheckFilled /></el-icon>新增</el-button>
                </el-col>
                <el-col :span="6" style="background-color: #EAF1F5">
                  <el-button type="primary" @click="" text><el-icon><CircleCloseFilled /></el-icon>删除</el-button>
                </el-col>
                <el-col :span="6" style="background-color: #EAF1F5">
                  <el-button type="primary" @click="" text><el-icon><Refresh /></el-icon>刷新</el-button>
                </el-col>
              </el-row>


              <el-table :data="inspectlist"
                        style="width: 100%"
                        :key="tableKey">
                <el-table-column prop="name" label="项目名称" width="180" />
                <el-table-column prop="price" label="项目价格" width="180" />
                <el-table-column prop="result" label="检查结果" width="180" />
                <el-table-column prop="status" label="状态" width="180" />
              </el-table>
            </el-tab-pane>
          </el-tabs>
        </el-col>
      </el-row>
    </el-card>


    <!--新增检查项的对话框  BEGIN-->
    <el-dialog v-model="inspectVisible" title="新增检查项" width="500">
      <el-table :data="inspectitemlist"
                style="width: 100%"
                :key="tableKey"
                highlight-current-row
                @current-change="">
        <el-table-column prop="name" label="项目名称" width="200" />
        <el-table-column prop="price" label="项目价格" width="200" />
      </el-table>
    </el-dialog>
    <!--新增检查项的对话框  END-->
  </div>
</template>

<script setup lang="ts">

import {Search,Refresh,CircleCheckFilled,Tickets,CircleCloseFilled} from "@element-plus/icons-vue";
import RegisterByType from "@/views/menzhenyisheng/component/RegisterByType.vue";
import {defineProps, defineEmits, toRefs, ref} from 'vue'
import {useUserStore} from "@/store/modules/user";
import MenzhenAPI from "@/api/menzhenyisheng/menzhen";
import InspectAPI from "@/api/menzhenyisheng/inspect";


let inspectlist = ref([]);
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
let record = ref({
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
let chosenregister = ref({
  casenumber:'',
  id:'',
});
let inspectVisible = ref(false);
let inspectitemlist = ref([]);


function handleChildSelected(value:any){
  chosenregister.value = value;
  console.log(value)
}

function refresh(){
  tableKey.value = Date.now();
}



function submit(){
  let params = record;
  params.value.casenumber = chosenregister.value.casenumber;
  params.value.registid= chosenregister.value.id;

  console.log(params.value.casenumber);

  if(params.value.casenumber === undefined || params.value.casenumber === null || params.value.casenumber === ''){
    ElMessage.error('请先选择患者！');
  } else{
    MenzhenAPI.add_record(params.value).then(
      (data) => {
        console.log(data);
      }
    )
  }
}

function showinspcetitem() {
  InspectAPI.getallinspect().then(
    (data) => {
      console.log(data);
      inspectitemlist.value = data;
      inspectVisible.value = true;
    })
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
