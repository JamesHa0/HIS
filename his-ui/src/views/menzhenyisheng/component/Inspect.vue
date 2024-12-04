<template>
  <div>
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




    <!--新增检查项的对话框  BEGIN-->
    <el-dialog v-model="inspectVisible" title="选择检查项" width="500">
      <el-table :data="inspectitemlist"
                style="width: 100%"
                :key="tableKey"
                @selection-change="onSelectedItems"
                ref="refItemTable"
      >
        <el-table-column type="selection" width="100" />
        <el-table-column prop="name" label="项目名称" width="200" />
        <el-table-column prop="price" label="项目价格" width="200" />
      </el-table>
      <template #footer>
        <div>
          <el-button @click="onCancel">取消</el-button>
          <el-button type="primary" @click="addtoRegist()">
            保存
          </el-button>
        </div>
      </template>
    </el-dialog>
    <!--新增检查项的对话框  END-->
  </div>
</template>

<script setup lang="ts">

import {CircleCheckFilled, CircleCloseFilled, Refresh, Tickets} from "@element-plus/icons-vue";
import {ref} from "vue";
import InspectAPI from "@/api/menzhenyisheng/inspect";

let inspectlist = ref([]);
let tableKey = ref(0);

let inspectVisible = ref(false);
let inspectitemlist = ref([]);

let currentSelectedRow = ref({});

// 检查项选中多行
let selectedItemRows = ref([]);

// 检查项的选中状态
let refItemTable = ref();



// 接收当前选中的患者信息
const refreshCurrentSelRegister = (info:any) =>{
  currentSelectedRow = info;
  console.log("当前选中患者信息：",currentSelectedRow)
}
defineExpose({
  refreshCurrentSelRegister
})


// 检查项复选框选中事件
const onSelectedItems = (selection: any) =>{
  selectedItemRows = selection;
  console.log(selectedItemRows);
}



function showinspcetitem() {
  InspectAPI.get_all_inspect().then(
    (data) => {
      console.log(data);
      inspectitemlist.value = data;
      inspectVisible.value = true;
    })
}

function addtoRegist(){
  // 判断是否选中患者
  console.log("选中的id：",currentSelectedRow.id)
  if(currentSelectedRow === undefined || currentSelectedRow == null || !currentSelectedRow.id){
    alert("请先选择患者");
    return;
  } else{
    // 获取选中的行数据
    let params = reactive([]);
    for(let i = 0; i < selectedItemRows.length;i++){
      let row = selectedItemRows[i];
      let oneline = reactive({
        inspectid:row.inspectid,
        name:row.name,
        price:row.price,
        registid:currentSelectedRow.id,
        status:1,
      })
      params.push(oneline);
    }
    if (params.length === 0){
      alert("请先选择检查项");
    } else{
      let strParams = JSON.stringify(params);
      let toServletParams = {
        data:strParams
      }
      InspectAPI.add_many_apply(toServletParams).then(
        (data) => {
          console.log(data);
        }
      )
      clearItemSels;
      inspectVisible.value = false;
      alert("检查项已添加！");
    }
  }


}

// 取消按钮
function onCancel(){
  clearItemSels;
  inspectVisible.value = false;
}

// 清空选中多行检查项
function clearItemSels(){
  inspectitemlist.value.forEach(row => {
    refItemTable.value.toggleRowSelection(row, false);
  });
}

</script>

<style scoped>

</style>
