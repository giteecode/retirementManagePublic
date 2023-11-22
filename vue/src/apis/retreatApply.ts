import { http } from "@/utils";
import { IPageSearchElderByKey } from "@/apis/bookManage";

interface IPageRetreatApplyByKey {
  bedName: string;
  elderName: string;
  elderSex: string;
  idNum: string;
}

interface IAddRetreatApply {
  elderId: number;
}

// 分页查询退住申请
export async function pageRetreatApplyByKey(data: IPageRetreatApplyByKey) {
  return http.get("/api/retreatApply/pageRetreatApplyByKey", {
    params: {
      ...data
    }
  });
}

// 分页搜索老人
export async function pageSearchElderByKey(data: IPageSearchElderByKey) {
  return http.get("/api/retreatApply/pageSearchElderByKey", {
    params: {
      ...data
    }
  });
}

// 新增退住申请
export function addRetreatApply(data: IAddRetreatApply) {
  return http.post("/api/retreatApply/addRetreatApply", data);
}
