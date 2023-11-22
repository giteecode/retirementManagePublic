import { http } from "@/utils";
import { IPageSearchElderByKey } from "@/apis/bookManage";
import { IPageDishesByKey } from "@/apis/dishes";

interface IPageOrderByKey {
  elderName:string;
  elderPhone: string;
}

interface IAddOrder {
  elderId: string;
  dineType: string;
  dineDate: string;
  orderDishesList: any;
}

interface IGetOrderById {
  dishesId: string;
}

interface ISendOrder {
  id: string;
  deliverDishesDate: string;
  staffId: string;
}

// 就餐方式
export const IDineTypeList = [
  { label: "送餐", value: "送餐" },
  { label: "堂食", value: "堂食" }
];

// 分页查询点餐
export async function pageOrderByKey(data: IPageOrderByKey) {
  // 因为后台返回的字段与前端表单数据的prop不一样，但是组件封装是需要一样的，所以请求前增加一些这两个字段
  // Reflect.has(data, 'name') ? (data.gradeName = data.name) : ''
  // Reflect.has(data, 'type') ? (data.nurseType = data.type) : ''
  return http.get("/api/order/pageOrderByKey", {
    params: {
      ...data
    }
  });
}

// 分页搜索老人
export function pageSearchElderByKey(data: IPageSearchElderByKey) {
  return http.get('/api/order/pageSearchElderByKey', {
    params: {
      ...data
    }
  })
}

// 分页查询菜品
export async function pageDishesByKey(data: IPageDishesByKey) {
  return http.get("/api/order/pageDishesByKey",{
    params:{
      ...data
    }
  });
}

// 新增点餐
export function addOrder(data: IAddOrder) {
  return http.post("/api/order/addOrder", data);
}

// 根据编号查询点餐
export async function getOrderById(data: IGetOrderById) {
  return http.get("/api/order/getOrderById", {
    params: {
      ...data
    }
  });
}

// 护理人员
export async function listNurseStaff() {
  return http.get("/api/order/listNurseStaff");
}

// 送餐
export function sendOrder(data: ISendOrder) {
  return http.put("/api/order/sendOrder", data);
}
