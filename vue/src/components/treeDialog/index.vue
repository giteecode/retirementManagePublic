<template>
  <el-dialog
    style="width: 70%"
    v-model="dialogVisible"
    title="选择床位"
    destroy-on-close
  >
    <el-tree
      :data="data"
      :props="defaultProps"
      accordion
      @node-click="checkBed"
    />
  </el-dialog>
</template>

<script setup lang="ts" name="useProTable">
import { ref } from "vue";
import { getBuildTree } from "@/apis/bookManage";

const data: any = ref();
const dialogVisible = ref(false);
const drawerProps = ref<DialogProps>();

interface DialogProps {
  treeApi: (params: any) => Promise<any>;
}

const defaultProps = {
  id: "id",
  label: "name",
  children: "childrenList"
};

// 接收父组件传过来的参数
const treeAcceptParams = async (params: DialogProps) => {
  drawerProps.value = params;
  dialogVisible.value = true;
  const res: any = await getBuildTree();
  data.value = res.data;
};
// 将方法主动暴露出来
defineExpose({
  treeAcceptParams
});

// const emit = defineEmits(['getCheckElderInfo'])
const emit = defineEmits<{
  (event: "getCheckBedInfo", val: any): void
}>();
// 向父组件传值
const checkBed = (bed: any) => {
  if (bed.level === 4) {
    emit("getCheckBedInfo", bed);
    dialogVisible.value = false;
  }
};
</script>

<style lang="scss" scoped></style>
