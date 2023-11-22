import { http } from "@/utils";

interface IPageRoomTypeByKey {
  name: string;
  roomTypeName: string;
}

interface IAddRoomType {
  id: string;
  name: string;
  monthPrice: string;
}

interface IGetRoomTypeById {
  roomTypeId: string;
}

// 分页查询房间类型
export async function pageRoomTypeByKey(data: IPageRoomTypeByKey) {
  // 因为后台返回的字段与前端表单数据的prop不一样，但是组件封装是需要一样的，所以请求前增加一些这两个字段
  Reflect.has(data, "name") ? (data.roomTypeName = data.name) : "";
  return http.get("/api/roomType/pageRoomTypeByKey", {
    params: {
      ...data
    }
  });
}

// 新增房间类型
export function addRoomType(data: IAddRoomType) {
  return http.post("/api/roomType/addRoomType", data);
}

// 根据编号查询房间类型
export async function getRoomTypeById(data: IGetRoomTypeById) {
  return http.get("/api/roomType/getRoomTypeById", {
    params: {
      ...data
    }
  });
}

// 编辑房间类型
export function editRoomType(data: IAddRoomType) {
  return http.put("/api/roomType/editRoomType", data);
}

// 删除房间类型
export async function deleteRoomType(data: IGetRoomTypeById) {
  return http.delete("/api/roomType/deleteRoomType", {
    params: {
      ...data
    }
  });
}
