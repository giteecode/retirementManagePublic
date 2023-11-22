<template>
  <template v-for="menu in menuList" :key="menu.id">
    <el-sub-menu
      v-if="menu.children && menu.children.length > 0"
      :index="menu.name + ''"
    >
      <template #title>
        <svg-icon
          v-if="menu.meta.icon"
          :icon="menu.meta.icon"
          class="sub-menu-icon"
        ></svg-icon>
        <el-tooltip
          effect="light"
          :content="menu.meta.title"
          placement="top-start"
          :offset="-10"
          :disabled="!showTooltip"
        >
          <span ref="menuTextRef" @mouseover="hoverMenu">{{
            menu.meta.title
          }}</span>
        </el-tooltip>
      </template>
      <MenuItem :menuList="menu.children" />
    </el-sub-menu>
    <el-menu-item
      :index="menu.name + ''"
      @click="handleMenuClick(menu.name)"
      v-else
    >
      <svg-icon
        v-if="menu.meta.icon"
        :icon="menu.meta.icon"
        class="sub-menu-icon"
      ></svg-icon>
      <template #title>
        <el-tooltip
          effect="light"
          :content="menu.meta.title"
          placement="top-start"
          :offset="-10"
          :disabled="!showTooltip"
        >
          <span ref="menuTextRef" @mouseover="hoverMenu">{{
            menu.meta.title
          }}</span>
        </el-tooltip>
      </template>
    </el-menu-item>
  </template>
</template>

<script setup lang="ts">
import { defineProps, ref } from 'vue'
import { IRoute } from '@/router/types'
import router from '@/router'

export interface Props {
  menuList: IRoute[]
}

defineProps<Props>()

const showTooltip = ref(false)

const handleMenuClick = (name: string) => {
  router.push({ name })
}

// 判断是否显示tooltip
const hoverMenu = (e: Event) => {
  const target = e.target as HTMLSpanElement
  // 判断文本是否溢出
  showTooltip.value = target.scrollWidth > target.clientWidth
}
</script>

<style lang="scss" scoped></style>
