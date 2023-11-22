<template>
  <el-config-provider :locale="locale">
    <router-view />
  </el-config-provider>
</template>
<script setup lang="ts">
import { ElConfigProvider } from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

const locale = zhCn
</script>

<style lang="scss" scoped>
.app {
  width: 100%;
  height: 100%;
}
</style>
