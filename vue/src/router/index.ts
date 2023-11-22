import { createRouter, RouteRecordRaw, createWebHashHistory } from 'vue-router'
import Layout from '@/layout/index.vue'
import store from '@/store'
import { initRoutes } from './utils'

// 静态路由
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/home',
    children: []
  },
  {
    path: '/login',
    name: 'Login',
    meta: {
      title: '登录'
    },
    component: () =>
      import(/* webpackChunkName: "Login" */ '@/views/login/index.vue')
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    meta: {
      title: '404'
    },
    component: () =>
      import(/* webpackChunkName: "404" */ '@/views/error/404.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

const ROUTER_WHITE_LIST = ['/login']

router.beforeEach(async (to, from, next) => {
  // 设置页面标题
  document["title"] = to.meta.title + " | 敬老院管理系统"

  // .判断访问页面是否在路由白名单地址中，如果存在直接放行
  if (ROUTER_WHITE_LIST.includes(to.path)) return next()

  //.判断 是否有 Token，没有重定向到 login
  if (!store.state.app.token) return next({ path: '/login', replace: true })

  //如果没有初始化动态路由就初始化
  if (!store.state.app.hasAuth && store.state.app.token) {
    await initRoutes()
    return next({ path: to.path })
  }

  // 7.正常访问页面
  next()
})

export default router
