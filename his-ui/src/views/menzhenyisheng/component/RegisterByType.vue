<template>
  <el-table :data="list"
            style="width: 100%"
            highlight-current-row
            @current-change="row_change"
            ref = "tableRef"
  >
    <el-table-column prop="casenumber" label="病历号" width="125" />
    <el-table-column prop="realname" label="姓名" width="125" />
    <el-table-column prop="age" label="年龄" width="80" />
  </el-table>

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



const emit = defineEmits(['selectone']);

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

defineExpose({
  clearSelect
})


function getbybelong(){
  MenzhenAPI.get_by_belong(msg.value).then(
    (data:any) => {
      list.value = data;
    })
}
onMounted(()=>{
  getbybelong()
})
</script>

<style scoped>

</style>
