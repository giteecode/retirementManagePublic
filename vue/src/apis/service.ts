import { http } from "@/utils";

interface IGetServiceType {
  serviceTypeName: string;
}

export interface IPageServiceByKey {
  name: string;
  typeId: number;
}

interface IAddServiceType {
  id: number;
  name: string;
}

interface IGetServiceTypeById {
  serviceTypeId: string;
}

interface IAddService {
  id: number;
  name: string;
  needDate: string;
  price: string;
  typeId: number;
  chargeMethod: string;
}

export interface IGetServiceById {
  serviceId: string;
}

// 收费方式
export const IChargeMethodList = [
  { label: "按次", value: "按次" },
  { label: "按月", value: "按月" }
];

// 获取服务类型列表
export async function getServiceType(data: IGetServiceType) {
  return http.get("/api/service/getServiceType", {
    params: {
      ...data
    }
  });
}

// 分页查询服务
export async function pageServiceByKey(data: IPageServiceByKey) {
  // 因为后台返回的字段与前端表单数据的prop不一样，但是组件封装是需要一样的，所以请求前增加一些这两个字段
  // Reflect.has(data, 'roleName') ? (data.roleId = data.roleName) : ''
  return http.get("/api/service/pageServiceByKey", {
    params: {
      ...data
    }
  });
}

// 新增服务类型
export function addServiceType(data: IAddServiceType) {
  return http.post("/api/service/addServiceType", data);
}

// 根据编号获取服务类型
export async function getServiceTypeById(data: IGetServiceTypeById) {
  return http.get("/api/service/getServiceTypeById", {
    params: {
      ...data
    }
  });
}

// 编辑服务类型
export function editServiceType(data: IAddServiceType) {
  return http.put("/api/service/editServiceType", data);
}

// 删除服务类型
export async function deleteServiceType(data: IGetServiceTypeById) {
  return http.delete("/api/service/deleteServiceType", {
    params: {
      ...data
    }
  });
}

// 新增服务
export function addService(data: IAddService) {
  return http.post("/api/service/addService", data);
}

// 根据编号获取服务
export async function getServiceById(data: IGetServiceById) {
  return http.get("/api/service/getServiceById", {
    params: {
      ...data
    }
  });
}

// 编辑服务
export function editService(data: IAddService) {
  return http.put("/api/service/editService", data);
}

// 删除服务
export async function deleteService(data: IGetServiceById) {
  return http.delete("/api/service/deleteService", {
    params: {
      ...data
    }
  });
}
