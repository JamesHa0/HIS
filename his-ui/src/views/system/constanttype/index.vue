<template>
  <div>
  <el-card class="card1">
    <el-row>
      <el-col :span="8">
        <el-input
          v-model="searchkey"
          style="max-width: 400px"
          placeholder="请输入常数类别编码或名称"
          class="input-with-select"
          @keyup.enter="search(searchkey)"
        >
        <template #append>
          <el-button :icon="Search" @click="search(searchkey)"/>
        </template>
        </el-input>
      </el-col>
      <el-col :span="6">
        <el-button type="primary" :icon="Edit" @click="addFormVisible = true">新增常数类别</el-button>
      </el-col>
    </el-row>
  </el-card>
  <el-card>
          <el-table :data="list" border style="width: 100%" :key="tableKey">
            <el-table-column prop="id" label="ID" width="150" />
            <el-table-column prop="constanttypecode" label="类别编码" width="180" />
            <el-table-column prop="constanttypename" label="类别名称" width="180" />
            <el-table-column fixed="right" label="操作" min-width="120">
              <template #default="scope">
                <el-button link type="primary" size="small" @click="handleEdit(scope.row)">
            编辑
          </el-button>
          <el-button link type="primary" size="small" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>



<!--编辑常数项的对话框  BEGIN-->
  <el-dialog v-model="editFormVisible" title="编辑常数项" width="500" @closed="handleEditFormClosed">
    <el-form :model="editForm">
      <el-form-item label="常数项编码" :label-width="100">
        <el-input v-model="editForm.constanttypecode" />
      </el-form-item>
      <el-form-item label="常数项名称" :label-width="100">
        <el-input v-model="editForm.constanttypename" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="editFormVisible = false">取消</el-button>
        <el-button type="primary" @click="handleEditFormClosed()">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>
<!--编辑常数项的对话框  END-->


<!--新增常数项的对话框  BEGIN-->
  <el-dialog v-model="addFormVisible" title="新增常数项" width="500">
    <el-form :model="addForm">
      <el-form-item label="常数项编码" :label-width="100">
        <el-input v-model="addForm.constanttypecode" />
      </el-form-item>
      <el-form-item label="常数项名称" :label-width="100">
        <el-input v-model="addForm.constanttypename" />
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
<!--新增常数项的对话框  END-->
  </div>
</template>

<script setup lang="ts">

import {Edit, Search} from '@element-plus/icons-vue'
import ConstanttypeAPI from "@/api/system/constanttype";

let tableKey = ref(0);
let searchkey = ref();
let list = ref([]);
let editFormVisible = ref(false);
let addFormVisible = ref(false);
let beforeEditForm = ref({
  constanttypecode: '',
  constanttypename: ''
});
let editForm = ref({
  constanttypecode: '',
  constanttypename: ''
});
let addForm = reactive({
  constanttypecode: '',
  constanttypename: ''
});

function handleEdit(row:any){
  beforeEditForm = row;
  editForm = row;
  editFormVisible.value = true;
}


// 模糊搜索
function search (searchkey: string){
  console.log(searchkey)
  let params = {
    searchkey: searchkey
  }
  ConstanttypeAPI.searchByKey(params)
    .then(
      (data:any) => {
        list.value = data;
        tableKey.value = Date.now();
      }
    )
}



function handleDelete(row:any){
  ElMessageBox.confirm("确认删除?", "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  }).then(function (){
    ConstanttypeAPI.delete(row)
      .then(
        (data:any) => {
          //刷新页面
          ConstanttypeAPI.getAll()
            .then(
              (data:any) => {
                list.value = data;
                tableKey.value = Date.now();
              }
            )
        }
      )
  })
}

function handleEditFormClosed(){
  //使用确定按钮 关闭常数项编辑对话框的回调函数：给后端发请求，修改常数项
  editFormVisible.value = false;
  console.log(editForm)
  ConstanttypeAPI.update(editForm)
    .then(
      (data:any) => {
        //刷新页面
          ConstanttypeAPI.getAll()
            .then(
              (data:any) => {
                list.value = data;
                tableKey.value = Date.now();
              }
            )
      }
    )
}



function handleAddFormClosed(){
//使用确定按钮 关闭常数项新增对话框的回调函数：给后端发请求，新增常数项
  addFormVisible.value = false;
  console.log(addForm)
  ConstanttypeAPI.add(addForm)
    .then(
      (data:any) => {
        //刷新页面
          ConstanttypeAPI.getAll()
            .then(
              (data:any) => {
                list.value = data;
                tableKey.value = Date.now();
              }
            )
      }
    )
}


onMounted( ()=>{
  ConstanttypeAPI.getAll()
    .then(
      (data:any) => {
        list.value = data;
      }
    )
})
</script>

<style scoped>

.el-card{
  margin: 10px;
  padding: 10px;
}
</style>
