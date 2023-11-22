import { http } from "@/utils";
import { IPageSearchElderByKey } from "@/apis/bookManage";

interface IPageAccidentByKey {
  elderName: string;
  staffName: string;
}

interface IAddAccident {
  id: number;
  elderId: number;
  staffId: number;
  occurDate: string;
  description: string;
  picture: string;
}

interface IGetAccidentById {
  accidentId: string;
}

interface IEditAccident {
  id: number;
  name: string;
  phone: string;
  relation: string;
  accidentDateStr: string;
  accidentNum: number;
}

// 分页查询事故登记
export async function pageAccidentByKey(data: IPageAccidentByKey) {
  return http.get("/api/accident/pageAccidentByKey", {
    params: {
      ...data
    }
  });
}

// 分页搜索老人
export async function pageSearchElderByKey(data: IPageSearchElderByKey) {
  return http.get("/api/accident/pageSearchElderByKey", {
    params: {
      ...data
    }
  });
}

// 获取护工列表
export async function listAccidentStaff() {
  return http.get("/api/accident/listAccidentStaff");
}

// 新增事故登记
export function addAccident(data: IAddAccident) {
  return http.post("/api/accident/addAccident", data);
}

// 根据编号获取事故登记
export async function getAccidentById(data: IGetAccidentById) {
  return http.get("/api/accident/getAccidentById", {
    params: {
      ...data
    }
  });
}

// 编辑事故登记
export function editAccident(data: IEditAccident) {
  return http.put("/api/accident/editAccident", data);
}

// 删除事故登记
export async function deleteAccident(data: IGetAccidentById) {
  return http.delete("/api/accident/deleteAccident", {
    params: {
      ...data
    }
  });
}
