import { http } from "@/utils";
import { IPageSearchElderByKey } from "@/apis/bookManage";

interface IPageOutwardByKey {
  elderName: string;
  chaperoneType: string;
  startTime: string;
  endTime: string;
}

interface IListContactByElderId {
  elderId: string;
}

interface IAddOutward {
  elderId: number;
  chaperoneName: string;
  chaperonePhone: string;
  chaperoneType: string;
  outwardDate: string;
  planReturnDate: string;
}

interface IGetOutwardById {
  outwardId: string;
}

interface IDelayReturn {
  id: string;
  planReturnDate: string;
}

interface IRecordReturn {
  id: string;
  realReturnDate: any;
}

// 陪同人类型
export const typeList = [
  { label: "护工", value: "护工" },
  { label: "家属", value: "家属" }
];

// 分页查询外出登记
export async function pageOutwardByKey(data: IPageOutwardByKey) {
  return http.get("/api/outward/pageOutwardByKey", {
    params: {
      ...data
    }
  });
}

// 分页搜索老人
export async function pageSearchElderByKey(data: IPageSearchElderByKey) {
  return http.get("/api/outward/pageSearchElderByKey", {
    params: {
      ...data
    }
  });
}

// 获取护工列表
export async function listOutwardStaff() {
  return http.get("/api/outward/listOutwardStaff");
}

// 获取紧急联系人列表
export async function listContactByElderId(data: IListContactByElderId) {
  return http.get("/api/outward/listContactByElderId", {
    params: {
      ...data
    }
  });
}

// 新增外出登记
export function addOutward(data: IAddOutward) {
  return http.post("/api/outward/addOutward", data);
}

// 根据编号获取外出登记
export async function getOutwardById(data: IGetOutwardById) {
  return http.get("/api/outward/getOutwardById", {
    params: {
      ...data
    }
  });
}

// 延期返回
export function delayReturn(data: IDelayReturn) {
  return http.put("/api/outward/delayReturn", data);
}

// 登记返回
export function recordReturn(data: IRecordReturn) {
  return http.put("/api/outward/recordReturn", data);
}

// 删除外出登记
export async function deleteOutward(data: IGetOutwardById) {
  return http.delete("/api/outward/deleteOutward", {
    params: {
      ...data
    }
  });
}
