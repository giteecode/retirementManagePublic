import { http } from "@/utils";

interface IPageSourceByKey {
  name: string;
  sourceName: string;
}

interface IAddSource {
  id: string;
  name: string;
}

interface IGetSourceById {
  sourceId: string;
}

// 分页查询来源渠道
export async function pageSourceByKey(data: IPageSourceByKey) {
  // 因为后台返回的字段与前端表单数据的prop不一样，但是组件封装是需要一样的，所以请求前增加一些这两个字段
  Reflect.has(data, "name") ? (data.sourceName = data.name) : "";
  return http.get("/api/source/pageSourceByKey", {
    params: {
      ...data
    }
  });
}

// 新增来源渠道
export function addSource(data: IAddSource) {
  return http.post("/api/source/addSource", data);
}

// 根据编号查询来源渠道
export async function getSourceById(data: IGetSourceById) {
  return http.get("/api/source/getSourceById", {
    params: {
      ...data
    }
  });
}

// 编辑来源渠道
export function editSource(data: IAddSource) {
  return http.put("/api/source/editSource", data);
}

// 删除来源渠道
export async function deleteSource(data: IGetSourceById) {
  return http.delete("/api/source/deleteSource", {
    params: {
      ...data
    }
  });
}
