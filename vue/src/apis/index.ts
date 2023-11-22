// 动态路由的假数据 可删除

import { http } from '@/utils'

// 问题：何时发起请求？ 在动态设置路由的时候（data => 树形结构 => 路由列表）

function getUserRouteList(uid: number) {
  return http
    .post('/api/user_router_list', { uid })
    .then((data) => data)
    .catch((err) => {
      throw err
    })
}

export { getUserRouteList }
