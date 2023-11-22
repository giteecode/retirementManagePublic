<template>
  <div>
    <el-dialog
      style="width: 70%"
      v-model="dialogVisible"
      :title="drawerProps.title"
      destroy-on-close
    >
      <div>
        <el-form
          :model="formData"
          class="login-form"
          ref="ruleFormRef"
          :rules="rules"
          label-width="120px"
        >
          <div class="flex justify-around flex-wrap">
            <div class="w-full md:w-1/2">
              <el-form-item label="楼层名称:" prop="name">
                <el-input v-model="formData.name" clearable />
              </el-form-item>
            </div>
            <div class="w-full md:w-1/2">
              <el-form-item label="房间数量:" prop="roomNum">
                <el-input v-model="formData.roomNum" clearable />
              </el-form-item>
            </div>
            <!-- 占位 -->
            <div
              class="md:w-1/2"
              v-for="i in 1"
              :key="i"
              style="height: 0"
            ></div>
          </div>
          <div class="flex flex-row-reverse">
            <div></div>
          </div>
        </el-form>
      </div>
      <template v-if="!drawerProps.isView" #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button
          class="bg-blue"
          type="primary"
          @click="handleSubmit"
        >
          提交
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref, watch } from "vue";
import { ElMessage, FormInstance, FormRules } from "element-plus";
import { integerRule, numberRule, stringRule } from "@/utils/formRules";
import { getRoomTypeById } from "@/apis/roomType";
import { getBuildingById, getFloorById } from "@/apis/build";

const ruleFormRef = ref<FormInstance | null>(null);
const dialogVisible = ref(false);

watch(dialogVisible, (value, oldValue, onCleanup) => {
  if (!value) {
    formData.value = {
      id: "",
      name: "",
      roomNum: "",
      buildingId: "",
      floorLimit: ""
    };
  }
});

const rules = reactive<FormRules>({
  name: [
    {
      required: true,
      validator(rule, value, callback) {
        stringRule(rule, value, callback, "楼层名称", 2, 20);
      },
      trigger: "blur"
    }
  ],
  roomNum: [
    {
      required: true,
      validator(rule, value, callback) {
        integerRule(rule, value, callback, "房间数量", 1, 50);
      },
      trigger: "blur"
    }
  ]
});

interface DialogProps {
  title: string;
  isView: boolean;
  rowData?: any;
  api?: (params: any) => Promise<any>;
  getTableList?: () => Promise<any>;
}

const drawerProps = ref<DialogProps>({
  isView: false,
  title: ""
});
const formData = ref({
  id: "",
  name: "",
  roomNum: "",
  buildingId: "",
  floorLimit: ""
});

// 接收父组件传过来的参数
const floorAcceptParams = async (params: DialogProps) => {
  drawerProps.value = params;
  if (drawerProps.value.title !== "新增楼层") {
    const res: any = await getFloorById({
      floorId: params.rowData.id
    });
    formData.value = res.data;
  }else {
    formData.value = params.rowData
  }
  dialogVisible.value = true;
};

const emits = defineEmits(["operateNode"]);

//点击提交
const handleSubmit = () => {
  ruleFormRef.value!.validate(async valid => {
    if (!valid) return;
    try {
      const res = await drawerProps.value.api!(formData.value);
      if (res.code == 200) {
        ElMessage.success({
          message: res.msg
        });
        emits("operateNode");
        dialogVisible.value = false;
      } else {
        ElMessage.error({
          message: res.msg
        });
      }
    } catch (error) {
      console.log(error);
    }
  });
};

defineExpose({
  floorAcceptParams
});
</script>
<style scoped lang="scss">
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
