import { http } from "@/utils";

interface IListRoomByKey {
  buildingId: string;
  floorId: string;
  elderName: string;
}

// 获取楼栋列表
export async function listBuilding() {
  return http.get("/api/bedPanorama/listBuilding");
}

// 获取楼层列表
export function listFloorByBuildingId(buildingId: string) {
  return http.get("/api/bedPanorama/listFloorByBuildingId", {
    params: {
      buildingId
    }
  });
}

// 获取房间列表
export function listRoomByKey(data: IListRoomByKey) {
  return http.get("/api/bedPanorama/listRoomByKey", {
    params: {
      ...data
    }
  });
}
