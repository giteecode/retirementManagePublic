import { http } from "@/utils";
import { IPageServiceByKey } from "@/apis/service";

interface IPageNurseGradeByKey {
  name:string;
  gradeName: string;
  type:string;
  nurseType: string;
}

interface IAddNurseGrade {
  id: number;
  name: string;
  type: string;
  monthPrice: number;
  serviceIdList: any ;
}

interface IGetNurseGradeById {
  nurseGradeId: string
}

// 护理类型
export const INurseTypeList = [
  { label: "自理", value: "自理" },
  { label: "介护", value: "介护" },
  { label: "全护", value: "全护" }
];

// 分页查询护理等级
export async function pageNurseGradeByKey(data: IPageNurseGradeByKey) {
  // 因为后台返回的字段与前端表单数据的prop不一样，但是组件封装是需要一样的，所以请求前增加一些这两个字段
  Reflect.has(data, 'name') ? (data.gradeName = data.name) : ''
  Reflect.has(data, 'type') ? (data.nurseType = data.type) : ''
  return http.get("/api/nurseGrade/pageNurseGradeByKey", {
    params: {
      ...data
    }
  });
}

// 获取服务类型
export function listServiceType() {
  return http.post("/api/nurseGrade/listServiceType");
}

// 分页查询服务
export async function pageServiceByKey(data: IPageServiceByKey) {
  return http.get("/api/nurseGrade/pageServiceByKey", {
    params: {
      ...data
    }
  });
}

// 新增护理等级
export function addNurseGrade(data: IAddNurseGrade) {
  return http.post("/api/nurseGrade/addNurseGrade", data);
}

// 根据编号查询护理等级
export async function getNurseGradeById(data: IGetNurseGradeById) {
  return http.get("/api/nurseGrade/getNurseGradeById", {
    params: {
      ...data
    }
  });
}

// 编辑护理等级
export function editNurseGrade(data: IAddNurseGrade) {
  return http.put("/api/nurseGrade/editNurseGrade", data);
}

// 删除护理等级
export async function deleteNurseGrade(data: IGetNurseGradeById) {
  return http.delete("/api/nurseGrade/deleteNurseGrade", {
    params: {
      ...data
    }
  });
}
