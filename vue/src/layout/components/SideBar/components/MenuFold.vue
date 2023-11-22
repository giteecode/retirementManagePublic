<template>
  <div class="container">
    <el-tooltip effect="light" :content="content" placement="right">
      <svg-icon
        icon="menu-fold"
        @click="changeSiderType"
        :style="style"
      ></svg-icon>
    </el-tooltip>
  </div>
</template>

<script setup lang="ts">
import { useStore } from '@/store'
import { computed } from 'vue'

const store = useStore()

const content = computed(() => {
  return store.state.app.siderType ? '点击折叠' : '点击展开'
})

const style = computed(() => {
  return store.state.app.siderType ? 'none' : 'transform: rotateY(180deg);'
})

const changeSiderType = () => {
  store.commit('app/setSiderType')
}
</script>

<style lang="scss" scoped>
.svg-icon {
  color: var(--el-color-primary);
  margin: 0 0 4px 16px !important;
  vertical-align: middle;
  outline: none;
  transition-duration: 0.36s;
  cursor: pointer;
}
</style>
