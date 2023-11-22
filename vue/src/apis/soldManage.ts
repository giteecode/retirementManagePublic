import { http } from '@/utils'

interface IAddConsult {
  address: string
  age: string
  consultContent: string
  consultDate: string
  consultName: string
  consultPhone: string
  elderName: string
  elderPhone: string
  idNum: string
  relation: string
  sex: string
  sourceId: string | number
  staffId: string | number
  consultId?: string | number
  elderId?: string | number
}

interface ISearhFormConsultByKey {
  pageNum: number
  pageSize: number
  consultName?: string
  consultPhone?: string
  elderName?: string
  elderPhone?: string
  endTime?: string
  sourceId?: string
  staffId?: string
  startTime?: string
  sourceName?: string
  staffName?: string
}

interface IConsultByForm {
  consultId: string | number
  elderId: string | number
}

// 获取咨询管理表格数据  根据咨询人编号和老人编号获取咨询信息
export async function getConsultByForm(data: IConsultByForm) {
  return http.get('/api/consult/getConsultByConsultIdAndElderId', {
    params: {
      ...data
    }
  })
}
//新增资询
export function addConsult(data: IAddConsult) {
  return http.post('/api/consult/addConsult', data)
}

// 删除咨询
export function delConsult(elderId: string | number) {
  return http.delete('/api/consult/deleteConsult', {
    params: {
      elderId
    }
  })
}

//编辑咨询
export function editConsult(data: IAddConsult) {
  return http.put('/api/consult/editConsult', data)
}

// 转为意向客户
export function intentionConsult(elderId: string | number) {
  return http.put('/api/consult/intentionConsult', {
    data: {
      elderId
    }
  })
}

// 来源渠道
export function listConsultSource() {
  return http.get('/api/consult/listConsultSource')
}

// 接待人
export function listConsultStaff() {
  return http.get('/api/consult/listConsultStaff')
}

// 分页查询咨询
export async function pageConsultByKey(data: ISearhFormConsultByKey) {
  // 因为后台返回的字段与前端表单数据的prop不一样，但是组件封装是需要一样的，所以请求前增加一些这两个字段
  Reflect.has(data, 'sourceName') ? (data.sourceId = data.sourceName) : ''
  Reflect.has(data, 'staffName') ? (data.staffId = data.staffName) : ''
  const res = await http.get('/api/consult/pageConsultByKey', {
    params: {
      ...data
    }
  })
  return res
}

//意向用户接口
interface ISearhFormIntentionByKey {
  pageNum: number
  pageSize: number
  elderName?: string
  elderPhone?: string | number
  labelId?: number
}

//分页查询意向客户
export async function pageIntentionByKey(data: ISearhFormIntentionByKey) {
  const res = await http.get('/api/intention/pageIntentionByKey', {
    params: {
      ...data
    }
  })
  return res
}

// 新增沟通记录
export function addCommunicationRecord(data: any) {
  return http.post('/api/intention/addCommunicationRecord', data)
}

// 新增意向客户
export function addIntention(data: any) {
  return http.post('/api/intention/addIntention', data)
}

// 新增回访计划
export function addVisitPlan(data: any) {
  return http.post('/api/intention/addVisitPlan', data)
}

// 新增沟通记录
export function deleteCommunicationRecord(communicationRecordId: any) {
  return http.delete('/api/intention/deleteCommunicationRecord', {
    params: {
      communicationRecordId
    }
  })
}

// 删除回访计划
export function deleteVisitPlan(visitPlanId: any) {
  return http.delete('/api/intention/deleteVisitPlan', {
    params: {
      visitPlanId
    }
  })
}

// 编辑沟通记录
export function editCommunicationRecord(data: any) {
  return http.put('/api/intention/editCommunicationRecord', data)
}

// 编辑意向客户
export function editIntention(data: any) {
  return http.put('/api/intention/editIntention', data)
}

// 编辑老人标签
export function editElderLabel(data: any) {
  return http.put('/api/intention/editElderLabel', data)
}

// 执行回访计划
export function executeVisitPlan(data: any) {
  return http.put('/api/intention/executeVisitPlan', data)
}

// 根据编号获取编辑意向客户标签
export function getEditElderLabelById(data: any) {
  return http.get('/api/intention/getEditElderLabelById', {
    params: {
      ...data
    }
  })
}

// 根据编号获取意向客户标签
export function getElderLabelById(data: any) {
  return http.get('/api/intention/getElderLabelById', {
    params: {
      ...data
    }
  })
}

// 根据编号获取意向客户
export function getIntentById(data: any) {
  return http.get('/api/intention/getIntentById', {
    params: {
      ...data
    }
  })
}

//客户标签
export function listLabel(data: any) {
  return http.get('/intention/listLabel', {
    params: {
      ...data
    }
  })
}

// 分页查询沟通记录
export function pageCommunicationRecord(data: any) {
  return http.get('/intention/pageCommunicationRecord', {
    params: {
      ...data
    }
  })
}

// 分页搜索老人
export function pageSearchElderByKey(data: any) {
  return http.get('/intention/pageSearchElderByKey', {
    params: {
      ...data
    }
  })
}

// 分页查询回访计划
export function pageVisitPlan(data: any) {
  return http.get('/intention/pageVisitPlan', {
    params: {
      ...data
    }
  })
}
