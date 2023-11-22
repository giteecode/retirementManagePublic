import { http } from '@/utils'

interface ILoginForm {
  pass: string
  phone: string
}

interface ISendCodeForm {
  pass: string
  account: string
}

interface IForgetPass {
  code: string
  pass: string
  account: string
}

interface IEditPass {
  newPass: string
  oldPass: string
}

export class IEditPassImpl implements IEditPass {
  newPass: string
  oldPass: string
  constructor(newPass: string, oldPass: string) {
    this.newPass = newPass
    this.oldPass = oldPass
  }
}

// 登录
export function getLogin(data: ILoginForm) {
  return http.post('/api/account/login', data)
}

// 发送验证码
export async function sendCode(data: ISendCodeForm) {
  return http.get('/api/account/sendCode', {
    params: {
      ...data
    }
  })
}

// 忘记密码
export async function forgetPass(data: IForgetPass) {
  return http.put('/api/account/forget', data)
}

// 修改密码
export async function editPass(data: IEditPass) {
  return http.put('/api/account/edit', data)
}

// 退出登录
export async function getLogout() {
  return http.delete('/api/account/logout')
}
