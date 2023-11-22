import { InjectionKey } from 'vue'
import { createStore, useStore as baseUseStore, Store } from 'vuex'
import { IRootState } from '@/store/types'
import { store as app } from './modules/app'
import { CommonStore } from './utils'
import createPersistedState from 'vuex-persistedstate'

export const modules = {
  app
}

// injectionKey：约束state类型
export const key: InjectionKey<Store<IRootState>> = Symbol()

const store = createStore<IRootState>({
  modules,
  plugins: [
    createPersistedState({
      key: 'stateData',
      paths: ['app']
    })
  ]
}) as CommonStore

export function useStore(): CommonStore {
  return baseUseStore(key)
}

export default store
