<template>
  <el-dialog
    style="width: 70%"
    v-model="dialogVisible"
    title="选择老人"
    destroy-on-close
  >
    <div class="table-box">
      <ProTable
        ref="proTable"
        title="用户列表"
        :columns="columns"
        :requestApi="getTableList"
      >
        <!-- 表格操作 -->
        <template #operation="scope">
          <el-popconfirm
            title="Are you sure to choose this?"
            @confirm="checkElder(scope.row)"
            confirm-button-type="warning"
          >
            <template #reference>
              <el-button size="small" link :icon="View" > 选择 </el-button>
            </template>
          </el-popconfirm>
        </template>
      </ProTable>
    </div>
  </el-dialog>
</template>

<script setup lang="ts" name="useProTable">
import { ref } from "vue";
import { ColumnProps } from "@/components/ProTable/interface";
import ProTable from "@/components/ProTable/index.vue";
import { View } from "@element-plus/icons-vue";

const dialogVisible = ref(false);
const proTable = ref();
const drawerProps = ref<DialogProps>();

interface DialogProps {
  elderApi: (params: any) => Promise<any>;
}

// 如果你想在请求之前对当前请求参数做一些操作，可以自定义如下函数：params 为当前所有的请求参数（包括分页），最后返回请求列表接口
// 默认不做操作就直接在 ProTable 组件上绑定	:requestApi="getUserList"
let getTableList = async (params: any) => {
  let newParams = JSON.parse(JSON.stringify(params));
  return drawerProps.value?.elderApi(newParams);
};

// 表格配置项
const columns: ColumnProps<any>[] = [
  { prop: "rank", label: "序号", width: 55 },
  { prop: "name", label: "姓名", search: { el: "input" } },
  { prop: "idNum", label: "身份证号" },
  { prop: "sex", label: "性别" },
  { prop: "age", label: "年龄" },
  { prop: "phone", label: "电话", search: { el: "input" } },
  { prop: "address", label: "地址" },
  { prop: "operation", label: "操作", width: 70 }
];

// 接收父组件传过来的参数
const elderAcceptParams = (params: DialogProps) => {
  drawerProps.value = params;
  dialogVisible.value = true;
};
// 将方法主动暴露出来
defineExpose({
  elderAcceptParams
});

// const emit = defineEmits(['getCheckElderInfo'])
const emit = defineEmits<{
  (event: "getCheckElderInfo", val: any): void
}>();
// 向父组件传值
const checkElder = (row: any) => {
  emit("getCheckElderInfo", row);
  dialogVisible.value = false;
};
</script>

<style lang="scss" scoped></style>
