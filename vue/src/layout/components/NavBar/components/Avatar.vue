<template>
  <el-dropdown trigger="click">
    <span class="navbar-bg-hover">
      <img :src="avator" />
      <p v-if="username" class="dark:text-black">{{ username }}</p>
    </span>
    <template #dropdown>
      <el-dropdown-menu class="logout">
        <el-dropdown-item @click="editPassShow">
          <svg-icon
            @click="editPassShow"
            icon="verify"
            size="14"
            style="margin-right: 5px"
          ></svg-icon>
          修改密码
        </el-dropdown-item>
        <el-dropdown-item @click="logout">
          <svg-icon
            @click="logout"
            icon="logout"
            size="14"
            style="margin-right: 5px"
          ></svg-icon>
          退出登录
        </el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
  <el-dialog v-model="editPassVisible" title="修改密码">
    <template #default>
      <el-form
        :model="formData"
        class="forget-pass-form"
        ref="ruleFormRef"
        :rules="editPassRules"
        size="large"
      >
        <el-form-item prop="oldPass">
          <el-input
            v-model="formData.oldPass"
            placeholder="旧密码"
            type="password"
            :prefix-icon="useRenderIcon('password', { size: 12 })"
            clearable
            show-password
          />
        </el-form-item>

        <el-form-item prop="newPass">
          <el-input
            v-model="formData.newPass"
            placeholder="新密码"
            type="password"
            :prefix-icon="useRenderIcon('password', { size: 12 })"
            clearable
            show-password
          />
        </el-form-item>

        <el-form-item prop="confirmPassword">
          <el-input
            v-model="formData.confirmPassword"
            placeholder="确认密码"
            type="password"
            :prefix-icon="useRenderIcon('password', { size: 12 })"
            clearable
            show-password
          />
        </el-form-item>
      </el-form>
    </template>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="editPassVisible = false">取消</el-button>
        <el-button
          class="bg-blue"
          type="primary"
          @click="handleEditPass(ruleFormRef)"
          :loading="loading"
        >
          修改
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import store from '@/store'
import { useRenderIcon } from '@/hooks/useIcons'
import { reactive, ref } from 'vue'
import { editPass, forgetPass, getLogout, IEditPassImpl } from '@/apis/user'
import { ElMessage, FormInstance, FormRules } from 'element-plus'

const avator = store.state.app.userPeofile.avator
const username = store.state.app.userPeofile.username
const editPassVisible = ref(false)
const ruleFormRef = ref<FormInstance | null>(null)
const loading = ref(false)
const formData = ref({
  oldPass: '12345',
  newPass: '123456',
  confirmPassword: '123456'
})

// 修改密码模态框显示
const editPassShow = () => {
  editPassVisible.value = true
}

// 修改密码
const handleEditPass = (formRef: FormInstance | null) => {
  loading.value = true

  if (!formRef) return
  formRef.validate(async (valid, fields) => {
    if (valid) {
      // const res: any = await editPass({
      //   oldPass: formData.value.oldPass,
      //   newPass: formData.value.newPass
      // })
      const res: any = await editPass(new IEditPassImpl('1', '1'))
      if (res.code === 200) {
        logout()
      } else {
        loading.value = false
        ElMessage({
          message: res.msg,
          type: 'warning'
        })
      }
    } else {
      loading.value = false
      return fields
    }
  })
}

// 退出登录
const logout = async () => {
  await getLogout()
  store.dispatch('app/logout')
  ElMessage({
    message: '操作成功',
    type: 'success'
  })
}

/* 修改密码校验 */
const editPassRules = reactive<FormRules>({
  oldPass: [
    {
      validator: (rule, value, callback) => {
        let oldPass = value?.trim()
        if (oldPass === '') {
          callback(new Error('旧密码不能为空'))
        } else {
          callback()
        }
      },
      trigger: 'blur'
    }
  ],
  newPass: [
    {
      validator: (rule, value, callback) => {
        const newPass = value?.trim()
        if (newPass === '') {
          callback(new Error('新密码不能为空'))
        } else {
          callback()
        }
      },
      trigger: 'blur'
    }
  ],
  confirmPassword: [
    {
      validator: (rule, value, callback) => {
        const confirmPass = value?.trim()
        const newPass = formData.value.newPass.trim()
        if (confirmPass === '') {
          callback(new Error('确认密码不能为空'))
        } else if (confirmPass !== newPass) {
          callback('与新密码不一致')
        } else {
          callback()
        }
      },
      trigger: 'blur'
    }
  ]
})
</script>

<style lang="scss" scoped>
.navbar-bg-hover {
  img {
    border-radius: 50%;
    height: 22px;
    width: 22px;
    margin-right: 10px;
  }
}
</style>
