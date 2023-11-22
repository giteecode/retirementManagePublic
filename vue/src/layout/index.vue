<template>
  <el-container
    class="layout-container"
    :class="{ hideSidebar: !$store.state.app.siderType }"
  >
    <el-aside :width="$store.state.app.siderType ? '230px' : '64px'">
      <SideBar />
    </el-aside>
    <el-container class="main-container">
      <el-header>
        <NavBar />
      </el-header>
      <el-main>
        <el-scrollbar>
          <router-view v-slot="{ Component }">
            <transition name="fade-transform" mode="out-in">
              <component :is="Component" />
            </transition>
          </router-view>
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup lang="ts">
import SideBar from './components/SideBar/index.vue'
import NavBar from './components/NavBar/index.vue'
import store from '@/store'

// window.onresize = () =>
//   (() => {
//     /** width app-wrapper类容器宽度
//      * 0 < width <= 760 隐藏侧边栏
//      * 760 < width <= 990 折叠侧边栏
//      * width > 990 展开侧边栏
//      */

//     let width = document.body.clientWidth

//     if (width > 0 && width <= 760) { // 隐藏侧边栏
//       store.commit('app/setDeviceType', 'phone')
//     } else if (width > 760 && width <= 990) { // 折叠侧边栏
//       store.commit('app/setDeviceType', 'ipaid')
//     } else if (width > 990) { // 展开侧边栏
//       store.commit('app/setDeviceType', 'desktop')
//     }
//   })()
</script>

<style lang="scss" scoped>
.layout-container {
  position: relative;
  width: 100%;
  height: 100%;
}

// 消除导航栏内边距
.el-header {
  position: relative;
  padding: 0 !important;
  height: 48px !important;
}

// 消除主内容内边距
.el-main {
  padding: 0 !important;
  background-color: #f0f2f5;
}

.el-scrollbar {
  background-color: rgb(246, 246, 246) !important;
  padding: 10px;
}
</style>
