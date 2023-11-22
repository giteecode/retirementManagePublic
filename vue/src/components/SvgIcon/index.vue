<template>
  <svg class="svg-icon" area-hidden="true">
    <use :xlink:href="iconName"></use>
  </svg>
</template>

<script setup lang="ts">
import { defineProps, computed } from 'vue'

const props = defineProps({
  icon: {
    type: String,
    required: true
  },
  size: {
    type: [Number, String],
    default: 16
  }
})

const iconName = computed(() => {
  return `#icon-${props.icon}`
})
const iconSize = computed(() => {
  return props.size + 'px'
})
</script>

<style lang="scss" scoped>
.svg-icon {
  width: 1em;
  height: 1em;
  fill: currentColor;
  font-size: v-bind(iconSize);
}
</style>
