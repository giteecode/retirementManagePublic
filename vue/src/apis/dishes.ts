import { http } from "@/utils";

interface IListDishesType {
  dishesTypeName: string;
}

export interface IPageDishesByKey {
  dishesName: string;
  typeId: number;
}

interface IAddDishesType {
  id: number;
  name: string;
}

interface IGetDishesTypeById {
  dishesTypeId: string;
}

interface IAddDishes {
  id: number;
  name: string;
  price: string;
  typeId: number;
}

export interface IGetDishesById {
  dishesId: string;
}

// 获取菜品分类列表
export async function listDishesType(data: IListDishesType) {
  return http.get("/api/dishes/listDishesType", {
    params: {
      ...data
    }
  });
}

// 分页查询菜品
export async function pageDishesByKey(data: IPageDishesByKey) {
  return http.get("/api/dishes/pageDishesByKey", {
    params: {
      ...data
    }
  });
}

// 新增菜品分类
export function addDishesType(data: IAddDishesType) {
  return http.post("/api/dishes/addDishesType", data);
}

// 根据编号获取菜品分类
export async function getDishesTypeById(data: IGetDishesTypeById) {
  return http.get("/api/dishes/getDishesTypeById", {
    params: {
      ...data
    }
  });
}

// 编辑菜品分类
export function editDishesType(data: IAddDishesType) {
  return http.put("/api/dishes/editDishesType", data);
}

// 删除菜品分类
export async function deleteDishesType(data: IGetDishesTypeById) {
  return http.delete("/api/dishes/deleteDishesType", {
    params: {
      ...data
    }
  });
}

// 新增菜品
export function addDishes(data: IAddDishes) {
  return http.post("/api/dishes/addDishes", data);
}

// 根据编号获取菜品
export async function getDishesById(data: IGetDishesById) {
  return http.get("/api/dishes/getDishesById", {
    params: {
      ...data
    }
  });
}

// 编辑菜品
export function editDishes(data: IAddDishes) {
  return http.put("/api/dishes/editDishes", data);
}

// 删除菜品
export async function deleteDishes(data: IGetDishesById) {
  return http.delete("/api/dishes/deleteDishes", {
    params: {
      ...data
    }
  });
}
