import { http } from "@/utils";

interface IPageBedByKey {
  buildId: string;
  floorId: string;
  roomId: string;
  bedFlag: string;
}

interface IAddBuilding {
  id: string;
  name: string;
  floorNum: string;
}

interface IGetBuildingById {
  buildingId: string;
}

interface IAddFloor {
  id: string;
  name: string;
  roomNum: string;
  buildingId: string;
  floorLimit: string;
}

interface IGetFloorById {
  floorId: string;
}

interface IAddRoom {
  id: string;
  name: string;
  typeId: string;
  bedNum: string;
  floorId: string;
  roomLimit: string;
}

interface IGetRoomById {
  roomId: string;
}

interface IDeleteNode {
  id: string;
  mark: string;
}

interface IAddBed {
  id: string;
  name: string;
  roomId: string;
  bedLimit: string;
}

interface IGetBedById {
  bedId: string;
}

// 床位状态
export const IBedFlagList = [
  { label: "空闲", value: "空闲" },
  { label: "预定", value: "预定" },
  { label: "入住", value: "入住" },
  { label: "退住审核", value: "退住审核" }
];

// 获取楼栋-楼层-房间树
export async function getNoBedTree() {
  return http.get("/api/build/getNoBedTree");
}

// 分页查询床位
export async function pageBedByKey(data: IPageBedByKey) {
  return http.get("/api/build/pageBedByKey", {
    params: {
      ...data
    }
  });
}

// 新增楼栋
export function addBuilding(data: IAddBuilding) {
  return http.post("/api/build/addBuilding", data);
}

// 根据编号获取楼栋
export async function getBuildingById(data: IGetBuildingById) {
  return http.get("/api/build/getBuildingById", {
    params: {
      ...data
    }
  });
}

// 编辑楼栋
export function editBuilding(data: IAddBuilding) {
  return http.put("/api/build/editBuilding", data);
}

// 新增楼层
export function addFloor(data: IAddFloor) {
  return http.post("/api/build/addFloor", data);
}

// 根据编号获取楼层
export async function getFloorById(data: IGetFloorById) {
  return http.get("/api/build/getFloorById", {
    params: {
      ...data
    }
  });
}

// 编辑楼层
export function editFloor(data: IAddFloor) {
  return http.put("/api/build/editFloor", data);
}

// 获取房间类型列表
export async function listRoomType() {
  return http.get("/api/build/listRoomType");
}

// 新增房间
export function addRoom(data: IAddRoom) {
  return http.post("/api/build/addRoom", data);
}

// 根据编号获取房间
export async function getRoomById(data: IGetRoomById) {
  return http.get("/api/build/getRoomById", {
    params: {
      ...data
    }
  });
}

// 编辑房间
export function editRoom(data: IAddRoom) {
  return http.put("/api/build/editRoom", data);
}

// 删除节点
export async function deleteNode(data: IDeleteNode) {
  return http.delete("/api/build/deleteNode", {
    params: {
      ...data
    }
  });
}

// 新增床位
export function addBed(data: IAddBed) {
  return http.post("/api/build/addBed", data);
}

// 根据编号获取床位
export async function getBedById(data: IGetBedById) {
  return http.get("/api/build/getBedById", {
    params: {
      ...data
    }
  });
}

// 编辑床位
export function editBed(data: IAddBed) {
  return http.put("/api/build/editBed", data);
}

// 删除床位
export async function deleteBed(data: IGetBedById) {
  return http.delete("/api/build/deleteBed", {
    params: {
      ...data
    }
  });
}
