<template>
  <div>
    <!--    整体布局-->
    <el-card>
      <!--      整体页面分为一行的左右两部分-->
      <el-row>
        <!--        左侧部分-->
        <el-col :span="8">
          <show-register
            @currentSelectedRow="handleChildSelected"
            ref="ChildSelectRegist"
          />
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
          <el-button type="primary" @click="payNow" :disabled="!paybuttonable">立即支付</el-button>
        </el-card>
        </el-col>

      </el-row>
    </el-card>

    <!--支付的对话框  BEGIN-->
    <el-dialog v-model="payVisible" title="支付窗口" width="500">
      <el-image :src=payImage style="width: 100%"></el-image>
      待支付金额：{{totalToPay}}
      <template #footer>
        <div>
          <el-button @click="onCancel">取消</el-button>
          <el-button type="primary" @click="payOk()">
            我已支付
          </el-button>
        </div>
      </template>
    </el-dialog>
    <!--支付的对话框  END-->
  </div>
</template>

<script setup lang="ts">

import {ref} from "vue";
import InspectAPI from "@/api/menzhenyisheng/inspect";
import {ElButton} from "element-plus";
import payImage from '@/views/guahaoshoufei/shoufei/QR.png';
import ShowRegister from "@/views/guahaoshoufei/component/showRegister.vue";

// 挂号列表子组件方法
const ChildSelectRegist = ref();

// 获取到的检查项列表
let inspectlist = ref([]);

// 当前选中的患者信息
let currentSelectedRow = ref({});

// tableKey
let tableKey = ref(0);



// 支付窗口按钮状态
let paybuttonable = ref(false);

// 待支付金额
let totalToPay = ref(0);

// 支付窗口显示属性
let payVisible = ref(false);


// 选择挂号
function handleChildSelected(value:any){
  // 挂号列表子组件返回数据
  if(currentSelectedRow.value){
    currentSelectedRow.value = value;
  }

  getInspectList()

}

// 获取选中患者的检查项列表
function getInspectList(){
  let param = {
    regist_id:currentSelectedRow.value.id
  }
  if(currentSelectedRow.value.id){
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
  let total = 0;
  inspectlist.value.forEach(row => {
    if(row.status == 1){
        total += row.price;
    }
  });
  totalToPay.value = total;
  if(total != 0){
    paybuttonable.value = true;
  } else {
    paybuttonable.value = false;
  }
  return ["待缴费金额：", total];
}

// 支付按钮
function payNow (){
  payVisible.value = true;
}

// 支付窗口取消
function onCancel(){
  getInspectList()
  payVisible.value = false;
}

// 支付窗口确定
function payOk(){
  console.log("currentSelectedRow",currentSelectedRow);
  let params = {
    regist_id:currentSelectedRow.value.id
  }
  InspectAPI.pay(params).then(
    (data) => {
      getInspectList()
      paybuttonable.value = false;
      payVisible.value = false;
      alert(data);
    }
  )
}


</script>

<style scoped>

</style>
