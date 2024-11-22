<template>
  <div>
    常数类别管理页面
  </div>
  <el-card class="card1">
    <el-row>
      <el-col :span="12">
        <el-input
          v-model="searchkey"
          style="max-width: 400px"
          placeholder="请输入常数类别编码或名称"
          class="input-with-select"
        ><template #append>
          <el-button :icon="Search" />
        </template>
        </el-input>
      </el-col>
      <el-col :span="6">
        <el-button type="success" :icon="Edit" >新增常数类别</el-button>
      </el-col>
    </el-row>
  </el-card>
  <el-card>
    <el-table :data="list" border style="width: 100%">
      <el-table-column prop="id" label="ID" width="180" />
      <el-table-column prop="constanttypecode" label="类别编码" width="180" />
      <el-table-column prop="constanttypename" label="类别名称" width="180" />
      <el-table-column fixed="right" label="操作" min-width="120">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="handleEdit(scope.row)">
            编辑
          </el-button>
          <el-button link type="primary" size="small" @click="handleDelete">删除</el-button>
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
</template>

<script setup lang="ts">

import {Edit, Search} from '@element-plus/icons-vue'
import ConstanttypeAPI from "@/api/system/constanttype";

let searchkey ="";
let list = ref([]);
let editFormVisible = ref(false);
let row;
let editForm = row;

function handleEdit(row:any){
  editForm = row;
  editFormVisible.value = true;
}
function handleDelete(){

}

function handleEditFormClosed(){
  //使用确定按钮 关闭常数项编辑对话框的回调函数：给后端发请求，修改常数项
  editFormVisible.value = false;
  ConstanttypeAPI.update(editForm)
    .then(
      (data:any) => {
        //刷新页面
        onMounted( ()=>{
          ConstanttypeAPI.getAll()
            .then(
              (data:any) => {
                list.value = data;
              }
            )
        })
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
.card1{
  display: flex;
  justify-content: space-between;
}
.el-card{
  margin: 10px;
  padding: 10px;
}
</style>
