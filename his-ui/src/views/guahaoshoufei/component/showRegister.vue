<template>
  <div>
    <el-row justify="space-between">
      <label class="label1">患者选择</label><el-button type="primary" :icon="Refresh" @click="refresh_register()"></el-button>
    </el-row>

    <el-input
      v-model="searchkey"
      style="max-width: 400px"
      placeholder="请输入患者名称"
      class="input-with-select"
      @keyup.enter="search(searchkey)"
    >
      <template #append>
        <el-button :icon="Search" @click="search(searchkey)"/>
      </template>
    </el-input>

    <!--          患者显示列表-->
    <el-card>
      <el-table :data="list"
                style="width: 100%"
                highlight-current-row
                @current-change="row_change"
                :key="tableKey"
                ref = "tableRef"
      >
        <el-table-column prop="casenumber" label="病历号" min-width="40%" />
        <el-table-column prop="realname" label="姓名" min-width="40%" />
        <el-table-column prop="age" label="年龄" min-width="20%" />
      </el-table>
      <el-pagination
        v-model:current-page="currentPage"
        :page-size="pageSize"
        :total="total"
        layout="total, prev, pager, next, jumper"
        @current-change="handleCurrentChange"
      />
    </el-card>
  </div>
</template>

<script setup lang="ts">

import GuahaoAPI from "@/api/guahaoshoufei/guahao";
import {Refresh, Search} from "@element-plus/icons-vue";
import {ElButton} from "element-plus";
import InspectAPI from "@/api/menzhenyisheng/inspect";
import {defineEmits} from "vue";



// 获取到的患者列表
let list = ref([]);

// 搜索内容
let searchkey = ref();

// 当前选中的患者信息
let currentSelectedRow = ref({});

// tableKey
let tableKey = ref(0);

// 当前页码，初始化为1
let currentPage = ref(1);
// 每页显示的记录数量，可根据实际需求调整，这里假设每页显示10条记录
let pageSize = ref(10);
// 总记录数，初始为0，后续会从后端获取并更新
let total = ref(0);


// 监听子组件的事件
const emit = defineEmits(['currentSelectedRow']);


// 页码改变时的处理方法
const handleCurrentChange = (newPage: number) => {
  currentPage.value = newPage;
  if(searchkey.value == "" || searchkey.value == null || searchkey.value === undefined){
    get_list();
  }else {
    console.log("searchkey:",searchkey.value);
    let params = {
      page: currentPage.value,
      size: pageSize.value,
      searchkey: searchkey.value
    }
    GuahaoAPI.search(params)
      .then(
        (data:any) => {
          console.log(data)
          list.value = data.list;
          total.value = data.total;
          tableKey.value = Date.now();
        }
      )
  }
};


// 获取全部挂号患者
function get_list() {
  console.log("currentPage.value:",currentPage.value);
  console.log("pageSize.value:",pageSize.value)
  let params = {
    page: currentPage.value,
    size: pageSize.value
  }
  GuahaoAPI.getall(params)
    .then(
      (data: any) => {
        list.value = data.list;
        total.value = data.total;
        tableKey.value = Date.now();
      }
    );
}

// 获取选中患者的信息
const row_change = (row:any) =>{
  currentSelectedRow = row;
  console.log("当前选中患者信息：",currentSelectedRow)
  emit('currentSelectedRow',currentSelectedRow);
}

//模糊搜索
function search (searchkey: string){
  console.log(searchkey)
  let params = {
    page: currentPage.value,
    size: pageSize.value,
    searchkey: searchkey
  }
  GuahaoAPI.search(params)
    .then(
      (data:any) => {
        console.log(data)
        list.value = data.list;
        total.value = data.total;
        currentPage.value = 1;
        tableKey.value = Date.now();
      }
    )
}

// 暴露给父组件的方法
defineExpose({
  row_change
})


// 刷新患者列表
function refresh_register(){
  tableKey.value = Date.now();
}


onMounted(()=>{
  get_list();
})
</script>

<style scoped>

</style>
