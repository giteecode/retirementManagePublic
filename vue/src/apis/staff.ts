import { http } from "@/utils";

interface IPageStaffByKey {
  roleId: number;
  roleName:number;
  name: string;
  phone: string;
}

interface IAddStaff {
  id: number;
  roleId: number;
  name: string;
  idNum: string;
  age: number;
  sex: string;
  phone: string;
  email: string;
  address: string;
  avator: string;
}

interface IGetStaffById {
  staffId: string
}

// 获取角色列表
export async function getRole() {
  return http.get("/api/staff/getRole");
}

// 分页查询员工
export async function pageStaffByKey(data: IPageStaffByKey) {
  // 因为后台返回的字段与前端表单数据的prop不一样，但是组件封装是需要一样的，所以请求前增加一些这两个字段
  Reflect.has(data, 'roleName') ? (data.roleId = data.roleName) : ''
  return http.get("/api/staff/pageStaffByKey", {
    params: {
      ...data
    }
  });
}

// 新增员工
export function addStaff(data: IAddStaff) {
  return http.post("/api/staff/addStaff", data);
}

// 根据老人编号查询员工
export async function getStaffById(data: IGetStaffById) {
  return http.get("/api/staff/getStaffById", {
    params: {
      ...data
    }
  });
}

// 编辑员工
export function editStaff(data: IAddStaff) {
  return http.put("/api/staff/editStaff", data);
}

// 删除员工
export async function leaveStaff(data: IGetStaffById) {
  return http.delete("/api/staff/leaveStaff", {
    params: {
      ...data
    }
  });
}
