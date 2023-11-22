import { ActionContext } from 'vuex'

import { IRootState } from '@/store/types'
import { IAppState } from './types'
import { IRoute } from '@/router/types'
import { getRouterList } from '@/mock/getters'
import { clearRoutes, formatRouteTree } from '@/router/utils'
// import { getUserRouteList } from '@/apis'
import { getLogin } from '@/apis/user'
import router from '@/router'

const defaultState = {
  uid: 1,
  token: '',
  hasAuth: false,
  routeTree: [],
  siderType: true,
  userPeofile: {},
  rememberPWD: false
}

export const store = {
  namespaced: true,
  state: {
    uid: 1,
    token: '',
    hasAuth: false,
    routeTree: [],
    siderType: true,
    userPeofile: {},
    rememberPWD: false
  },
  mutations: {
    setToken(state: IAppState, token: string) {
      state.token = token
    },
    setRouteTree(state: IAppState, routeTree: IRoute[]) {
      state.routeTree = routeTree
    },
    setAuth(state: IAppState, auth: boolean) {
      state.hasAuth = auth
    },
    setSiderType(state: IAppState) {
      state.siderType = !state.siderType
    },
    setUserProfile(state: IAppState, userPeofile: any) {
      state.userPeofile = userPeofile
    },
    setRememberPWD(state: IAppState, rememberPWD: boolean) {
      state.rememberPWD = rememberPWD
    },
    clearStore(state: IAppState) {
      Object.assign(state, defaultState)
    }
  },
  actions: {
    //登录
    async actionLogin(
      { commit, state }: ActionContext<IAppState, IRootState>,
      data: any
    ) {
      const res: any = await getLogin(data)
      if (res.code === 200) {
        commit('setToken', res?.data?.token)
        commit('setUserProfile', {
          username: res?.data.name,
          userid: res?.data.id,
          avator: res?.data.avator,
          authIdList: res?.data.authIdList
        })
      }
      commit('setRememberPWD', data?.rememberPWD)
      return res
    },
    //获取权限菜单
    getRouterTree({ commit, state }: ActionContext<IAppState, IRootState>) {
      // 模拟数据
      const routeList = getRouterList(state.uid).data as unknown as IRoute[]
      const routeTree = formatRouteTree(routeList)
      commit('setRouteTree', routeTree)
      commit('setAuth', true)
    },
    // 点击登出
    logout({ commit, state }: ActionContext<IAppState, IRootState>) {
      commit('clearStore')
      localStorage.clear()
      clearRoutes()
      router.replace('/login')
    }
  }
}
