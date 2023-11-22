import { http } from "@/utils";
import { IPageSearchElderByKey } from "@/apis/bookManage";

interface IPageCheckContractByKey {
  name: string;
  sex: string;
  idNum: string;
}

interface IAddCheckContract {
  id: string;
  nursingGradeId: string;
  cateringSetId: string;
  bedId: string;
  name: string;
  idNum: string;
  age: string;
  sex: string;
  phone: string;
  address: string;
  staffId: string;
  signDate: string;
  startDate: string;
  endDate: string;
  operateEmergencyContactQueryList: IEmergencyContact[];
}

interface IEmergencyContact {
  name: string;
  phone: string;
  email: string;
  relation: string;
  receiveFlag: string;
}

interface IGetCheckContractById {
  elderId: string
}

// 分页查询入住签约
export async function pageCheckContractByKey(data: IPageCheckContractByKey) {
  return http.get("/api/checkContract/pageCheckContractByKey", {
    params: {
      ...data
    }
  });
}

// 分页搜索老人
export async function pageSearchElderByKey(data: IPageSearchElderByKey) {
  return http.get("/api/checkContract/pageSearchElderByKey", {
    params: {
      ...data
    }
  });
}

// 获取护理等级列表
export async function listNurseGrade() {
  return http.get("/api/checkContract/listNurseGrade");
}

// 根据编号查询护理等级
export async function getNurseGradeById(nurseGradeId: string) {
  return http.get("/api/checkContract/getNurseGradeById", {
    params: {
      nurseGradeId
    }
  });
}

// 获取餐饮套餐列表
export async function listCateringSet() {
  return http.get("/api/checkContract/listCateringSet");
}

// 根据编号查询餐饮套餐
export async function getCateringSetById(cateringSetId: string) {
  return http.get("/api/checkContract/getCateringSetById", {
    params: {
      cateringSetId
    }
  });
}

// 获取楼栋树
export async function getBuildTree() {
  return http.get("/api/checkContract/getBuildTree");
}

// 根据编号查询床位
export async function getBedById(bedId: string) {
  return http.get("/api/checkContract/getBedById", {
    params: {
      bedId
    }
  });
}

// 获取营销人员
export async function listReserveStaff() {
  return http.get("/api/checkContract/listReserveStaff");
}

// 新增入住签约
export function addCheckContract(data: IAddCheckContract) {
  return http.post("/api/checkContract/addCheckContract", data);
}

// 根据老人编号查询入住签约
export async function getCheckContractById(data: IGetCheckContractById) {
  return http.get("/api/checkContract/getCheckContractById", {
    params: {
      ...data
    }
  });
}

// 编辑入住签约
export function editCheckContract(data: IAddCheckContract) {
  return http.put("/api/checkContract/editCheckContract", data);
}

// 删除入住签约
export async function deleteCheckContract(data: IGetCheckContractById) {
  return http.delete("/api/checkContract/deleteCheckContract", {
    params: {
      ...data
    }
  });
}
