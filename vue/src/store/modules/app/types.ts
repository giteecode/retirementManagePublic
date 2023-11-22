import { IRoute } from '@/router/types'

export interface IAppState {
  uid: number
  token: string
  hasAuth: boolean
  routeTree: IRoute[]
  siderType: boolean
  userPeofile: any
  rememberPWD: boolean
}
