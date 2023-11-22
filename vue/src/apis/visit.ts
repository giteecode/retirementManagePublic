import { http } from "@/utils";
import { IPageSearchElderByKey } from "@/apis/bookManage";

interface IPageVisitByKey {
  elderName: string;
  visitName: string;
  visitPhone: string;
  visitFlag: string;
}

interface IAddVisit {
  id: number;
  elderId: number;
  name: string;
  phone: string;
  relation: string;
  visitDate: string;
  visitNum: number;
}

interface IGetVisitById {
  visitId: string;
}

interface IEditVisit {
  id: number;
  name: string;
  phone: string;
  relation: string;
  visitDateStr: string;
  visitNum: number;
}

interface IRecordLeave {
  id: string;
  leaveDate: any;
}

// 来访状态
export const typeList = [
  { label: "待离开", value: "待离开" },
  { label: "已离开", value: "已离开" }
];

// 分页查询来访登记
export async function pageVisitByKey(data: IPageVisitByKey) {
  return http.get("/api/visit/pageVisitByKey", {
    params: {
      ...data
    }
  });
}

// 分页搜索老人
export async function pageSearchElderByKey(data: IPageSearchElderByKey) {
  return http.get("/api/visit/pageSearchElderByKey", {
    params: {
      ...data
    }
  });
}

// 新增来访登记
export function addVisit(data: IAddVisit) {
  return http.post("/api/visit/addVisit", data);
}

// 根据编号获取来访登记
export async function getVisitById(data: IGetVisitById) {
  return http.get("/api/visit/getVisitById", {
    params: {
      ...data
    }
  });
}

// 编辑来访登记
export function editVisit(data: IEditVisit) {
  return http.put("/api/visit/editVisit", data);
}

// 登记离开
export function recordLeave(data: IRecordLeave) {
  return http.put("/api/visit/recordLeave", data);
}

// 删除来访登记
export async function deleteVisit(data: IGetVisitById) {
  return http.delete("/api/visit/deleteVisit", {
    params: {
      ...data
    }
  });
}
