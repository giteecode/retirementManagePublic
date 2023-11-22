/** 路由 */
export interface IRoute {
  id: number
  pid: number
  name: string
  path: string
  redirect?: string
  component: string
  meta: {
    title: string
    icon?: string
  }
  children?: IRoute[]
}
