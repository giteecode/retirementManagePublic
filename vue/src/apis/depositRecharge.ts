import { http } from "@/utils";
import { IPageSearchElderByKey } from "@/apis/bookManage";

interface IPageDepositRechargeByKey {
  idNum:string;
  name:string;
  elderName:string;
  phone: string;
  elderPhone: string;
}

interface IRecharge {
  elderId: string;
  amount: string;
}

// 分页查询预存充值
export async function pageDepositRechargeByKey(data: IPageDepositRechargeByKey) {
  // 因为后台返回的字段与前端表单数据的prop不一样，但是组件封装是需要一样的，所以请求前增加一些这两个字段
  Reflect.has(data, 'elderName') ? (data.name = data.elderName) : ''
  Reflect.has(data, 'elderPhone') ? (data.phone = data.elderPhone) : ''
  return http.get("/api/depositRecharge/pageDepositRechargeByKey", {
    params: {
      ...data
    }
  });
}

// 分页搜索老人
export function pageSearchElderByKey(data: IPageSearchElderByKey) {
  return http.get('/api/depositRecharge/pageSearchElderByKey', {
    params: {
      ...data
    }
  })
}

// 入住老人账户充值
export function recharge(data: IRecharge) {
  return http.put("/api/depositRecharge/recharge", data);
}
