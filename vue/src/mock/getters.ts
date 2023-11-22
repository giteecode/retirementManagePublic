import { routes, users, IRoute, IUser } from './index'
import store from '@/store'

// 获取路由列表
export const getRouterList = (uid: number) => {
  const authIdList: number[] = store.state.app.userPeofile.authIdList
  const routeList: any[] = routes.filter(route => authIdList.includes(route.id))
  if (uid) {
    // userInfo 有可能是 undefined
    const userInfo: IUser | undefined = users.find(user => user.id === uid)

    if (userInfo) {
      // const authRouteList: IRoute[] = []
      // rid = router id
      // userInfo.auth.map(rid => {
      //   routeList.map((route: IRoute) => {
      //     if (route.id === rid) {
      //       authRouteList.push(route)
      //     }
      //   })
      // })
      return {
        code: 0,
        msg: 'ok',
        data: routeList
      }
    } else {
      return {
        code: 1001,
        msg: 'No userInfo for this UID',
        data: null
      }
    }
  } else {
    return {
      code: 1002,
      msg: 'No UID received',
      data: null
    }
  }
}
