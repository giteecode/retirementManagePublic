import { http } from '@/utils'

interface ISearchFormReserveByKey {
  pageNum: number
  pageSize: number
  elderName?: string
  payerPhone?: string
}

export interface IPageSearchElderByKey {
  pageNum: number
  pageSize: number
  elderName?: string
  elderPhone?: string
}

interface IAddReserve {
  bedId: string
  deposit: string
  dueDate: string
  elderAddress: string
  elderAge: string
  elderName: string
  elderPhone: string
  elderSex: string
  idNum: string
  payerName: string
  payerPhone: string
  staffId: string
}

interface IGetReserveById {
  elderId: string
  reserveId: string
}

interface IRefund {
  reserveId: string
}

// 分页查询预定
export async function pageReserveByKey(data: ISearchFormReserveByKey) {
  return http.get('/api/reserve/pageReserveByKey', {
    params: {
      ...data
    }
  })
}

// 分页搜索老人
export function pageSearchElderByKey(data: IPageSearchElderByKey) {
  return http.get('/api/reserve/pageSearchElderByKey', {
    params: {
      ...data
    }
  })
}

// 获取营销人员
export function listReserveStaff() {
  return http.get('/api/reserve/listReserveStaff')
}

// 获取楼栋树
export function getBuildTree() {
  return http.get('/api/reserve/getBuildTree')
}

// 新增预定
export function addReserve(data: IAddReserve) {
  return http.post('/api/reserve/addReserve', data)
}

// 根据预定编号和老人编号获取预定信息
export function getReserveById(data: IGetReserveById) {
  return http.get('/api/reserve/getReserveByReserveIdAndElderId', {
    params: {
      ...data
    }
  })
}

// 退款
export function refund(data: IRefund) {
  return http.put('/api/reserve/refund',data)
}
