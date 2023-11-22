import { http } from "@/utils";

interface IPageRetreatAuditByKey {
  elderName: string;
  elderSex: string;
  idNum: string;
}

interface IGetElderFeeById {
  elderId: number;
}

interface IAuditElderFee {
  applyId: number;
  elderId: number;
  auditResult: number;
}

// 审核结果
export const IAuditResultList = [
  { label: "通过", value: "通过" },
  { label: "不通过", value: "不通过" }
];

// 分页查询退住审核
export async function pageRetreatAuditByKey(data: IPageRetreatAuditByKey) {
  return http.get("/api/retreatAudit/pageRetreatAuditByKey", {
    params: {
      ...data
    }
  });
}

// 根据编号获取老人费用详情
export async function getElderFeeById(data: IGetElderFeeById) {
  return http.get("/api/retreatAudit/getElderFeeById", {
    params: {
      ...data
    }
  });
}

// 审核老人费用详情
export function auditElderFee(data: IAuditElderFee) {
  return http.put("/api/retreatAudit/auditElderFee", data);
}
