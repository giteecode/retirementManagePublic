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
              <el-form-item label="房间名称:" prop="name">
                <el-input v-model="formData.name" clearable />
              </el-form-item>
            </div>
            <div class="w-full md:w-1/2">
              <el-form-item label="房间类型:" prop="typeId">
                <el-select
                  v-model="formData.typeId"
                  placeholder="请选择"
                  class="w-full"
                >
                  <el-option
                    clearable
                    :label="item.name"
                    :value="item.id"
                    v-for="item in roomTypeList"
                    :key="item.id"
                  />
                </el-select>
              </el-form-item>
            </div>
            <div class="w-full md:w-1/2">
              <el-form-item label="床位数量:" prop="bedNum">
                <el-input v-model="formData.bedNum" clearable />
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
import { getBuildingById, getFloorById, getRoomById, listRoomType } from "@/apis/build";

const ruleFormRef = ref<FormInstance | null>(null);
const dialogVisible = ref(false);
const roomTypeList = ref<any>([])

watch(dialogVisible, (value, oldValue, onCleanup) => {
  if (!value) {
    formData.value = {
      id: '',
      name: '',
      typeId: '',
      bedNum: '',
      floorId: '',
      roomLimit: ''
    };
  }
});

const rules = reactive<FormRules>({
  name: [
    {
      required: true,
      validator(rule, value, callback) {
        stringRule(rule, value, callback, "房间名称", 2, 30);
      },
      trigger: "blur"
    }
  ],
  typeId: [{ required: true, message: "请选择房间类型", trigger: "blur" }],
  bedNum: [
    {
      required: true,
      validator(rule, value, callback) {
        integerRule(rule, value, callback, "床位数量", 1, 8);
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
  id: '',
  name: '',
  typeId: '',
  bedNum: '',
  floorId: '',
  roomLimit: ''
});

// 接收父组件传过来的参数
const roomAcceptParams = async (params: DialogProps) => {
  drawerProps.value = params;
  if (drawerProps.value.title !== "新增房间") {
    const res: any = await getRoomById({
      roomId: params.rowData.id
    });
    formData.value = res.data;
  }else {
    formData.value = params.rowData
  }
  const res:any = await listRoomType()
  roomTypeList.value = res.data
  console.log(formData.value);
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
  roomAcceptParams
});
</script>
<style scoped lang="scss">
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
