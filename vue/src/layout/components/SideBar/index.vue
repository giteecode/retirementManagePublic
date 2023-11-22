<template>
  <div class="sidebar-container">
    <div class="logo-container">
      <router-link title="敬老院管理系统" to="/">
        <img class="sidebar-logo" src="@/assets/imgs/logo.png" />
        <span class="sidebar-title">敬老院管理系统</span>
      </router-link>
    </div>
    <el-scrollbar>
      <el-menu
        :active-text-color="variables.menuActiveText"
        :background-color="variables.menuBg"
        :default-active="active"
        text-color="#fefefea6"
        unique-opened
        :collapse="!$store.state.app.siderType"
      >
        <MenuItem :menuList="store.state.app.routeTree" />
      </el-menu>
    </el-scrollbar>
    <div class="menufold-container">
      <MenuFold />
    </div>
  </div>
</template>

<script setup lang="ts">
import MenuItem from './components/MenuItem.vue'
import MenuFold from './components/MenuFold.vue'
import variables from '@/styles/variables.module.scss'
import { ref, watch } from 'vue'
import { RouteRecordName, useRoute } from 'vue-router'
import store from '@/store'

const route = useRoute()

let active = ref<RouteRecordName>('Home')

// 监听地址栏变化
watch(
  () => route,
  newVal => {
    if (newVal.name) {
      active.value = newVal.name
    }
  },
  {
    immediate: true,
    deep: true
  }
)
</script>

<style lang="scss">
.logo-container {
  .router-link-active {
    display: flex;
    height: 48px;
    width: 100%;
    align-items: center;
    padding: 0 10px;
    background-color: #002140;
    flex-wrap: nowrap;

    .sidebar-logo {
      height: 30px;
      width: 30px;
    }

    .sidebar-title {
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      margin-left: 8px;
      font-size: 18px;
      color: #fff;
    }
  }
}

.menufold-container {
  bottom: 0;
  box-shadow: 0 0 6px -2px var(--el-color-primary);
  height: 40px;
  line-height: 40px;
  position: absolute;
  width: 100%;
  z-index: 999;
}
</style>
