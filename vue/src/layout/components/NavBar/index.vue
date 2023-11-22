<template>
  <div class="nav-container">
    <div class="nav-left">
      <Breadcrumb />
    </div>
    <div class="nav-right">
      <Avatar class="nav-item" />
    </div>
  </div>
</template>

<script setup lang="ts">
import Avatar from './components/Avatar.vue'
import Breadcrumb from './components/Breadcrumb.vue'
</script>

<style lang="scss" scoped>
.nav-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 48px;
  overflow: hidden;
  background-color: #fff;
  border-bottom: 1px solid #d8d8d860;

  .nav-right,
  .nav-left {
    display: flex;
  }
}
</style>
