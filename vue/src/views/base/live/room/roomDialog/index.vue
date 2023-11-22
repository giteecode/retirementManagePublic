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
              <el-form-item label="房间类型:" prop="name">
                <el-input v-model="formData.name" clearable />
              </el-form-item>
            </div>
            <div class="w-full md:w-1/2">
              <el-form-item label="月费:" prop="monthPrice">
                <el-input v-model="formData.monthPrice" clearable />
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
import { numberRule, stringRule } from "@/utils/formRules";
import { getRoomTypeById } from "@/apis/roomType";

const ruleFormRef = ref<FormInstance | null>(null);
const dialogVisible = ref(false);

watch(dialogVisible, (value, oldValue, onCleanup) => {
  if (!value) {
    formData.value = { id: "", name: "", monthPrice: "" };
  }
});

const rules = reactive<FormRules>({
  name: [
    {
      required: true,
      validator(rule, value, callback) {
        stringRule(rule, value, callback, "房间类型", 2, 10);
      },
      trigger: "blur"
    }
  ],
  monthPrice: [
    {
      required: true,
      validator(rule, value, callback) {
        numberRule(rule, value, callback, "月费", 1, 1000000);
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
  monthPrice: ""
});

// 接收父组件传过来的参数
const acceptParams = async (params: DialogProps) => {
  drawerProps.value = params;
  if (drawerProps.value.title !== "新增") {
    const res: any = await getRoomTypeById({
      roomTypeId: params.rowData.id
    });
    formData.value = res.data;
  }
  dialogVisible.value = true;
};

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
        drawerProps.value.getTableList!();
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
  acceptParams
});
</script>
<style scoped lang="scss">
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
