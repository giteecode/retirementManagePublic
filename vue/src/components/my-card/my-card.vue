<template>
  <div class="flex flex-col rounded card-wrap p-3">
    <div class="pb-2" v-if="title">{{ title }}</div>
    <slot></slot>
  </div>
</template>

<script lang="ts" setup>
defineProps<{
  title?: string
}>()
</script>

<style lang="scss" scoped>
.card-wrap {
  background-color: #ffffff;
  border: 1px solid #eee;
  font-size: 18px;
}
</style>
