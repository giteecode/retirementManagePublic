<template>
  <el-upload
    v-model:file-list="imageList"
    :action="requestUrl"
    :headers="{ token: token }"
    list-type="picture-card"
    :before-upload="uploadBefore"
    :on-error="handleError"
    :on-success="handleSuccess"
    :on-preview="handlePreview"
    :on-remove="handleRemove"
  >
    <el-icon>
      <Plus />
    </el-icon>
  </el-upload>

  <el-dialog v-model="dialogVisible">
    <img w-full :src="dialogImageUrl" alt="Preview Image" />
  </el-dialog>
</template>
<!--

TODO 上传图片（单 / 多）使用方法

// 1.导入组件
import elderListDialog from "@/components/elderListDialog/index.vue";

// 2.使用组件
<uploadImage :uploadParams="uploadParams" @setImageData='setImageData' />

// 3.定义变量和方法 single / multiple 任选一个
ref({
  uploadType: "single",
  imageList: ref<UploadUserFile[]>([])
});
const setImageData = (url: string) => {
  formData.value.picture = url;
};

// 4.显示单张上传的图片(多张图片时循环添加)
// 在加载上传组件之前清空图片列表
uploadParams.value.imageList = []
// 添加显示上传的图片
uploadParams.value.imageList.push({
  uid: 0,
  name: "0.jpeg",
  url: formData.value.image
});

-->
<script lang="ts" setup>
import store from "@/store";
import { baseUrl } from "@/utils/http";
import { ElMessage } from "element-plus";
import { Plus } from "@element-plus/icons-vue";
import { defineEmits, onMounted, ref, watch } from "vue";
import type { UploadUserFile, ElUpload } from "element-plus";
// page data
const dialogImageUrl = ref("");
const dialogVisible = ref(false);
const imageList = ref<UploadUserFile[]>([]);

// father component transfer to this date
const props = defineProps({
  uploadParams: Object
});
// 将即将渲染的图片添加到列表
onMounted(() => {
  props.uploadParams?.["imageList"].forEach((image: any) => {
    imageList.value.push(image);
  });
});

// http request data
const requestUrl = baseUrl + "file/uploadImg";
const token = store.state.app.token;

// return data
const imageUrlList = ref<any[]>([]);
// 获取父组件方法
const emits = defineEmits(["setImageData"]);
// 更新数据
const updateData = () => {
  // 更新列表
  imageUrlList.value = [];
  imageList.value.forEach(image => imageUrlList.value.push(image.url));
  // 封装图片数据
  const data = ref<any>();
  if (props.uploadParams?.["uploadType"] === "single") {
    data.value = imageUrlList.value[0];
  } else {
    data.value = imageUrlList;
  }
  // 调用方法设置图片
  emits("setImageData", data);
};

// 监听图片列表
watch(imageList, (value, oldValue, onCleanup) => {
  if (props.uploadParams?.["uploadType"] === "single" && imageList.value.length > 1) {
    imageList.value.splice(0, 1);
    updateData();
  }
});

// 上传之前
const uploadBefore = (file: any) => {
  const isJPG = file.type === "image/jpeg" || file.type === "image/png";
  const isLt2M = file.size / 1024 / 1024 < 2;

  if (!isJPG) {
    ElMessage.error("只能上传jpg/png文件！");
  }
  if (!isLt2M) {
    ElMessage.error("文件大小不能超过2MB！");
  }

  return isJPG && isLt2M;
};

// 上传失败
const handleError = (response: any) => {
  ElMessage.error(response.data.msg);
};

// 上传成功
const handleSuccess = (response: any, uploadFile: any) => {
  uploadFile.url = response.data.url;
  updateData();
};

// 删除图片
const handleRemove = async (uploadFile: any) => {
  imageList.value.filter((image) => image.uid !== uploadFile.uid);
  updateData();

  // TODO 根据【imageId : uploadFile.uid】调用删除数据库图片的接口
};

// 查看大图
const handlePreview = (uploadFile: any) => {
  dialogImageUrl.value = uploadFile.url!;
  dialogVisible.value = true;
};
</script>
