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

        </el-col>


        <!--        右侧部分-->
        <el-col :span="16" >
        <el-card>
          <el-table :data="inspectlist"
                    style="width: 100%"
                    :key="tableKey"
                    :summary-method="getSumPrice"
                    show-summary
          >
            <el-table-column prop="name" label="项目名称" min-width="40%" />
            <el-table-column prop="price" label="项目价格" min-width="30%" />
            <el-table-column prop="status" label="状态" min-width="30%" >
              <template #default="scope">
                <el-tag v-if="scope.row.status == 1" type="danger">待缴费</el-tag>
                <el-tag v-else-if="scope.row.status == 2" type="warning">已缴费</el-tag>
                <el-tag v-else-if="scope.row.status == 3" type="success">已检查</el-tag>
                <el-tag v-else-if="scope.row.status == 4" type="primary">已退费</el-tag>
                <el-tag v-else-if="scope.row.status == 0" type="info">已撤销</el-tag>
              </template>
            </el-table-column>
            <el-table-column>
            </el-table-column>
          </el-table>
        </el-card>

            <el-button type="primary" @click="payNow">立即支付</el-button>

        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script setup lang="ts">

import {CircleCloseFilled, Refresh, Search} from "@element-plus/icons-vue";
import {ref} from "vue";
import GuahaoAPI from "@/api/guahaoshoufei/guahao";
import InspectAPI from "@/api/menzhenyisheng/inspect";
import {ElButton} from "element-plus";


// 获取到的患者列表
let list = ref([]);

// 获取到的检查项列表
let inspectlist = ref([]);

// tableKey
let tableKey = ref(0);

// 当前页码，初始化为1
let currentPage = ref(1);
// 每页显示的记录数量，可根据实际需求调整，这里假设每页显示10条记录
let pageSize = ref(10);
// 总记录数，初始为0，后续会从后端获取并更新
let total = ref(0);

// 当前选中的患者信息
let currentSelectedRow = ref({});

// 页码改变时的处理方法
const handleCurrentChange = (newPage: number) => {
  currentPage.value = newPage;
  get_list();
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


// 获取选中患者的检查项
function row_change(row:any){
  currentSelectedRow = row;
  console.log("当前选中患者信息：",currentSelectedRow)

  let param = {
    regist_id:currentSelectedRow.id
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
  return ["待缴费金额：", totalToPay];
}

// 支付按钮
function payNow (){
  console.log("支付按钮被点击");
}

// 刷新患者列表
function refresh(){
  tableKey.value = Date.now();
}

onMounted(()=>{
  get_list();
})

</script>

<style scoped>

</style>
