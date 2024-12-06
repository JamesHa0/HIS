<template>
  <div>
    <el-row justify="center">
      <el-col :span="6" style="background-color: #EAF1F5">
        <el-button type="primary" @click="" :disabled="true" text><el-icon><Tickets /></el-icon>修改</el-button>
      </el-col>
      <el-col :span="6" style="background-color: #EAF1F5">
        <el-button type="primary" @click="showinspcetitem" :disabled="!addbuttonable" text><el-icon><CircleCheckFilled /></el-icon>新增</el-button>
      </el-col>
      <el-col :span="6" style="background-color: #EAF1F5">
        <el-button type="primary" @click="handleDelete" text><el-icon><CircleCloseFilled /></el-icon>删除</el-button>
      </el-col>
      <el-col :span="6" style="background-color: #EAF1F5">
        <el-button type="primary" @click="refresh" text><el-icon><Refresh /></el-icon>刷新</el-button>
      </el-col>
    </el-row>


    <el-table :data="inspectlist"
              style="width: 100%"
              :key="tableKey"
              :summary-method="getSumPrice"
              show-summary
              @selection-change="handleSelectionChange"
              ref="refTable"
              class="form1"
    >
      <el-table-column type="selection" :selectable="selectable" width="30" />
      <el-table-column prop="name" label="项目名称" width="180" />
      <el-table-column prop="price" label="项目价格" width="150" />
      <el-table-column prop="result" label="检查结果" width="280" >
        <template #default="scope">
          <el-input type="textarea" @input="changeResultable = true" @blur="saveResult(scope.row)" v-model="scope.row.result" :autosize="{ minRows: 2, maxRows: 5 }"/>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态" width="100" >
        <template #default="scope">
          <el-tag v-if="scope.row.status == 1" type="danger">待缴费</el-tag>
          <el-tag v-else-if="scope.row.status == 2" type="warning">已缴费</el-tag>
          <el-tag v-else-if="scope.row.status == 3" type="success">已检查</el-tag>
          <el-tag v-else-if="scope.row.status == 4" type="primary">已退费</el-tag>
          <el-tag v-else-if="scope.row.status == 0" type="info">已撤销</el-tag>
        </template>
      </el-table-column>
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
import MenzhenAPI from "@/api/menzhenyisheng/menzhen";

let inspectlist = ref([]);
let tableKey = ref(0);

let inspectVisible = ref(false);
let inspectitemlist = ref([]);

let currentSelectedRow = ref({});

// 检查项列表选中多行
let selectedRows = ref([]);

// 检查项列表的选中状态
let refTable = ref();

// 检查项结果是否被修改
let changeResultable = ref(false);

// 新增检查项按钮状态
let addbuttonable = ref(false);

// 新增检查项选中多行
let selectedItemRows = ref([]);

// 新增检查项的选中状态
let refItemTable = ref();



// 接收当前选中的患者信息
const refreshCurrentSelRegister = (info:any) =>{
  currentSelectedRow = info;
  console.log("当前选中患者信息：",currentSelectedRow)
  console.log("当前选中患者状态：",currentSelectedRow.visitstate)

  let param = {
    regist_id:currentSelectedRow.id
  }

  if(currentSelectedRow.visitstate > 1){
    addbuttonable.value = true;
  }else {
    addbuttonable.value = false;
  }

  if(currentSelectedRow.id){
    InspectAPI.get_by_registid(param).then(
      (data) => {
        console.log(data);
        inspectlist.value = data;
      }
    )
  }
}
defineExpose({
  refreshCurrentSelRegister
})

// 计算检查项的合计金额
const getSumPrice = () =>{
  let totalToPay = 0;
  let total = 0;
  inspectlist.value.forEach(row => {
    if(row.status != 0){
      if(row.status == 1){
        totalToPay += row.price;
      }
      total += row.price;
    }
  });
  return [null, "检查项目合计：", total, "待缴费金额：", totalToPay];
}

// 保存检查结果
function saveResult(row){
  // 只有在检查结果被修改的情况下才发送请求
  if(changeResultable.value) {
    let params = {
      itemid: row.itemid,
      result: row.result
    }
    console.log("params:", params)
    InspectAPI.update_result(params).then(
      (data) => {
        alert(data);
        changeResultable.value = false;
      })
  }
}

// 锁定已检查的项目，防止被删除
const selectable = (row: inspectlist.value) => {
  return row.status == 1
}

// 检查项列表选中事件
const handleSelectionChange = (row: any) => {
  selectedRows = row;
  console.log(selectedRows);
}

// 删除按钮
function handleDelete() {
  let params = [];
  // 判断是否选中患者
  console.log("选中的患者",currentSelectedRow)
  if(currentSelectedRow === undefined || currentSelectedRow == null || !currentSelectedRow.id){
    alert("请先选择患者");
    return;
  } else {
    for (let i = 0; i < selectedRows.length; i++) {
      let row = selectedRows[i];
      params.push(row.itemid);
    }
  }
  if (params.length === 0) {
    ElMessage.warning("请勾选删除项");
    return;
  }
  ElMessageBox.confirm("确认删除?", "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  }).then(function () {
    let strParams = JSON.stringify(params);
    let toServletParams = {
      data: strParams
    }
    InspectAPI.delete_many_apply(toServletParams).then(
      (data:any) => {
        console.log(data)
        refreshCurrentSelRegister(currentSelectedRow);
      })
  })
}

// 刷新按钮
function refresh(){
  refreshCurrentSelRegister(currentSelectedRow);
}

// 打开新增检查项对话框
function showinspcetitem() {
  InspectAPI.get_all_inspect().then(
    (data) => {
      console.log(data);
      inspectitemlist.value = data;
      inspectVisible.value = true;
    })
}

// 新增检查项对话框选中事件
const onSelectedItems = (selection: any) =>{
  selectedItemRows = selection;
  console.log(selectedItemRows);
}

// 新增检查项
function addtoRegist(){
  // 判断是否选中患者
  console.log("选中的患者：",currentSelectedRow)
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

// 清空新增检查项对话框的多行选中
function clearItemSels(){
  inspectitemlist.value.forEach(row => {
    refItemTable.value.toggleRowSelection(row, false);
  });
}

</script>

<style scoped>

</style>
