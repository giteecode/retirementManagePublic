import { http } from "@/utils";
import { IPageDishesByKey } from "@/apis/dishes";

interface IPageCateringSetByKey {
  name:string;
  setName: string;
}

interface IAddCateringSet {
  id: number;
  name: string;
  monthPrice: number;
  dishesIdList: any ;
}

interface IGetCateringSetById {
  setId: string
}

// 分页查询餐饮套餐
export async function pageCateringSetByKey(data: IPageCateringSetByKey) {
  // 因为后台返回的字段与前端表单数据的prop不一样，但是组件封装是需要一样的，所以请求前增加一些这两个字段
  Reflect.has(data, 'name') ? (data.setName = data.name) : ''
  return http.get("/api/cateringSet/pageCateringSetByKey", {
    params: {
      ...data
    }
  });
}

// 获取菜品分类
export function listDishesType() {
  return http.post("/api/cateringSet/listDishesType");
}

// 分页查询菜品
export async function pageDishesByKey(data: IPageDishesByKey) {
  return http.get("/api/cateringSet/pageDishesByKey", {
    params: {
      ...data
    }
  });
}

// 新增餐饮套餐
export function addCateringSet(data: IAddCateringSet) {
  return http.post("/api/cateringSet/addCateringSet", data);
}

// 根据编号查询餐饮套餐
export async function getCateringSetById(data: IGetCateringSetById) {
  return http.get("/api/cateringSet/getCateringSetById", {
    params: {
      ...data
    }
  });
}

// 编辑餐饮套餐
export function editCateringSet(data: IAddCateringSet) {
  return http.put("/api/cateringSet/editCateringSet", data);
}

// 删除餐饮套餐
export async function deleteCateringSet(data: IGetCateringSetById) {
  return http.delete("/api/cateringSet/deleteCateringSet", {
    params: {
      ...data
    }
  });
}
