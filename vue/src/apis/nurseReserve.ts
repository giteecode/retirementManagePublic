import { http } from "@/utils";
import { IPageSearchElderByKey } from "@/apis/bookManage";
import { IGetServiceById } from "@/apis/service";

interface IPageNurseReserveByKey {
  bedName: string;
  elderName:string;
  serviceName: string;
}

interface IAddNurseReserve {
  elderId: number;
  serviceName: string;
  needDate: number;
  servicePrice: number;
  chargeMethod: string;
  frequency: number;
  payAmount: number;
}

interface IExecuteNurseReserve {
  id: number;
  nurseDate: string;
  staffId: string;
}

// 分页查询护理预定
export async function pageNurseReserveByKey(data: IPageNurseReserveByKey) {
  // 因为后台返回的字段与前端表单数据的prop不一样，但是组件封装是需要一样的，所以请求前增加一些这两个字段
  // Reflect.has(data, 'name') ? (data.gradeName = data.name) : ''
  // Reflect.has(data, 'type') ? (data.nurseType = data.type) : ''
  return http.get("/api/nurseReserve/pageNurseReserveByKey", {
    params: {
      ...data
    }
  });
}

// 分页搜索老人
export function pageSearchElderByKey(data: IPageSearchElderByKey) {
  return http.get('/api/nurseReserve/pageSearchElderByKey', {
    params: {
      ...data
    }
  })
}

// 获取服务项目
export async function listService() {
  return http.get("/api/nurseReserve/listService");
}

// 新增护理预定
export function addNurseReserve(data: IAddNurseReserve) {
  return http.post("/api/nurseReserve/addNurseReserve", data);
}

// 根据编号查询护理预定
export async function getServiceById(data: IGetServiceById) {
  return http.get("/api/nurseReserve/getServiceById", {
    params: {
      ...data
    }
  });
}

// 护理人员
export async function listNurseStaff() {
  return http.get("/api/nurseReserve/listNurseStaff");
}

// 执行护理预定
export function executeNurseReserve(data: IExecuteNurseReserve) {
  return http.put("/api/nurseReserve/executeNurseReserve", data);
}
