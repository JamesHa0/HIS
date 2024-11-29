<template>
  <el-table :data="list"
            style="width: 100%"
            :key="tableKey"
            highlight-current-row
            @current-change="row_change">
    <el-table-column prop="casenumber" label="病历号" width="150" />
    <el-table-column prop="realname" label="姓名" width="180" />
    <el-table-column prop="age" label="年龄" width="180" />
  </el-table>

</template>

<script setup lang="ts">

import {defineProps,defineEmits,toRefs,ref} from 'vue'
import MenzhenAPI from "@/api/menzhenyisheng/menzhen";
const props = defineProps({
  msg: {
    type: Object,
    required: false,
    default: () => ({}),
  }
});
const {msg} = toRefs(props);

let tableKey = ref(0);
let list = ref([]);

function getbybelong(){
  MenzhenAPI.get_by_belong(msg.value).then(
    (data) => {
      list.value = data;
      console.log(list.value);
      tableKey.value = Date.now();
    })
}

const emit = defineEmits(['selectone']);

const row_change = (row:any) =>{
  emit('selectone',row);
}


onMounted(()=>{
  getbybelong()
})
</script>

<style scoped>

</style>
