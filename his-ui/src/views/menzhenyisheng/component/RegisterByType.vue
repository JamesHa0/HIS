<template>
  <el-table :data="list"
            style="width: 100%"
            highlight-current-row
            @current-change="row_change"
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

</template>

<script setup lang="ts">

import {defineProps,defineEmits,toRefs,ref} from 'vue'
import MenzhenAPI from "@/api/menzhenyisheng/menzhen";
const props = defineProps({
  msg: Object
});

const {msg} = toRefs(props);
let list = ref([]);
let tableRef = ref();

// 当前页码，初始化为1
let currentPage = ref(1);
// 每页显示的记录数量，可根据实际需求调整，这里假设每页显示10条记录
let pageSize = ref(6);
// 总记录数，初始为0，后续会从后端获取并更新
let total = ref(0);


const emit = defineEmits(['selectone']);

// 页码改变时的处理方法
const handleCurrentChange = (newPage: number) => {
  currentPage.value = newPage;
  getbybelong();
};

const row_change = (row:any) =>{
  if(row == null || row == undefined){
    return;
  }
  let line = row;
  line.orderid = msg.value.orderid;

  emit('selectone',line);
}
const clearSelect = () =>{
  tableRef.value.setCurrentRow();
}

const search = (searchkey: string) =>{
  let params = {
    belong: msg.value.belong,
    id: msg.value.id,
    state:msg?.value.state,
    page: currentPage.value,
    size: pageSize.value,
    searchkey: searchkey
  }
  MenzhenAPI.searchbyname(params).then(
    (data:any) => {
      list.value = data.list;
      total.value = data.total;
      currentPage.value = 1;
    })
}

defineExpose({
  clearSelect,
  search
})


function getbybelong(){

  let params = {
    belong: msg.value.belong,
    id: msg.value.id,
    state:msg?.value.state,
    page: currentPage.value,
    size: pageSize.value,
  }
  MenzhenAPI.get_by_belong(params).then(
    (data:any) => {
      list.value = data.list;
      total.value = data.total;
    })
}
onMounted(()=>{
  getbybelong()
})
</script>

<style scoped>

</style>
