// import { ref } from 'vue'
// import { useRoute, onBeforeRouteUpdate } from 'vue-router'
// import { useCookies } from '@vueuse/integrations/useCookies'
// import { router } from '~/router'

// export function useTabList() {
//   const route = useRoute()
//   const cookie = useCookies()

//   const activeTab = ref(route.path)
//   const tabList = ref([
//     {
//       title: '后台首页',
//       path: '/'
//     }
//   ])

//   // 添加标签导航
//   function addTab(tab) {
//     let noTab = tabList.value.findIndex((t) => t.path == tab.path) == -1
//     if (noTab) {
//       tabList.value.push(tab)
//     }

//     cookie.set('tabList', tabList.value)
//   }

//   // 初始化标签导航列表
//   function initTabList() {
//     let tbs = cookie.get('tabList')
//     if (tbs) {
//       tabList.value = tbs
//     }
//   }

//   initTabList()

//   onBeforeRouteUpdate((to, from) => {
//     activeTab.value = to.path
//     addTab({
//       title: to.meta.title,
//       path: to.path
//     })
//   })

//   const changeTab = (t) => {
//     activeTab.value = t
//     router.push(t)
//   }

//   const removeTab = (t) => {
//     let tabs = tabList.value
//     let a = activeTab.value
//     if (a == t) {
//       tabs.forEach((tab, index) => {
//         if (tab.path == t) {
//           const nextTab = tabs[index + 1] || tabs[index - 1]
//           if (nextTab) {
//             a = nextTab.path
//           }
//         }
//       })
//     }

//     activeTab.value = a
//     tabList.value = tabList.value.filter((tab) => tab.path != t)

//     cookie.set('tabList', tabList.value)
//   }

//   const handleClose = (c) => {
//     if (c == 'clearAll') {
//       // 切换回首页
//       activeTab.value = '/'
//       // 过滤只剩下首页
//       tabList.value = [
//         {
//           title: '后台首页',
//           path: '/'
//         }
//       ]
//     } else if (c == 'clearOther') {
//       // 过滤只剩下首页和当前激活
//       tabList.value = tabList.value.filter(
//         (tab) => tab.path == '/' || tab.path == activeTab.value
//       )
//     }
//     cookie.set('tabList', tabList.value)
//   }

//   return {
//     activeTab,
//     tabList,
//     changeTab,
//     removeTab,
//     handleClose
//   }
// }
