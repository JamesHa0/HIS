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
              <RegisterByType :msg="{id:doctorid,belong:'doc',state:'unvisit'}"/>
              <lebel class="label1">已诊患者：</lebel>
              <RegisterByType :msg="{id:doctorid,belong:'doc',state:'visit'}"/>
            </el-tab-pane>

            <el-tab-pane label="科室">
              <lebel class="label1">未诊患者：</lebel>
              <RegisterByType :msg="{id:deptid,belong:'dept',state:'unvisit'}"/>
              <lebel class="label1">已诊患者：</lebel>
              <RegisterByType :msg="{id:deptid,belong:'dept',state:'visit'}"/>
            </el-tab-pane>
          </el-tabs>
        </el-col>


<!--        右侧部分-->
        <el-col :span="16" >


<!--          标签页-->
          <el-tabs type="border-card">
            <el-tab-pane label="病例首页">
              <el-row justify="center">
                <el-col :span="6" style="background-color: #EAF1F5">
                  <el-button type="primary" @click="" text><el-icon><Tickets /></el-icon>暂存</el-button>
                </el-col>
                <el-col :span="6" style="background-color: #EAF1F5" >
                  <el-button type="primary" @click="" text><el-icon><CircleCheckFilled /></el-icon>提交</el-button>
                </el-col>
                <el-col :span="6" style="background-color: #EAF1F5">
                  <el-button type="primary" @click="" text><el-icon><Printer /></el-icon>清空所有</el-button>
                </el-col>
                <el-col :span="6" style="background-color: #EAF1F5">
                  <el-button type="primary" @click="" text><el-icon><Refresh /></el-icon>刷新</el-button>
                </el-col>
              </el-row>
              <el-form >
                <lebel class="label1">病史内容：</lebel>
                <el-form-item label="主诉" :label-width="100">
                  <el-input type="textarea" />
                </el-form-item>

                <el-form-item label="现病史" :label-width="100">
                  <el-input type="textarea" />
                </el-form-item>

                <el-form-item label="现病治疗情况" :label-width="100">
                  <el-input type="textarea" />
                </el-form-item>

                <el-form-item label="既往史" :label-width="100">
                  <el-input type="textarea" />
                </el-form-item>

                <el-form-item label="过敏史" :label-width="100">
                  <el-input type="textarea" />
                </el-form-item>

                <el-form-item label="体格检查" :label-width="100">
                  <el-input type="textarea" />
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
                  <el-input type="textarea" />
                </el-form-item>

                <el-form-item label="注意事项" :label-width="100">
                  <el-input type="textarea" />
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" @click="" text><el-icon><CircleCheckFilled /></el-icon>提交</el-button>
                </el-form-item>
              </el-form>




            </el-tab-pane>

            <el-tab-pane label="检查申请">
              //todo
            </el-tab-pane>
          </el-tabs>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script setup lang="ts">

import {Search,Refresh,CircleCheckFilled,Tickets,Printer} from "@element-plus/icons-vue";
import RegisterByType from "@/views/menzhenyisheng/component/RegisterByType.vue";
import {defineProps, defineEmits, toRefs, ref} from 'vue'
import {useUserStore} from "@/store/modules/user";

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



function refresh(){
  tableKey.value = Date.now();
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
