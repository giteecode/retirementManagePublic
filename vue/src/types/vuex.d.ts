// vuex.d.ts
import { Store, Module } from 'vuex'
import { type IStoreType } from './store/types'

// 模块扩展
// 模板内$store强类型支持
declare module '@vue/runtime-core' {
  // 为 `this.$store` 提供类型声明
  interface ComponentCustomProperties {
    $store: Store<IStoreType>
  }
}
