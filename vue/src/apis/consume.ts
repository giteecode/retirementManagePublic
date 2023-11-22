import { http } from "@/utils";

interface IPageConsumeByKey {
  elderName: string;
  startTime: string;
  endTime: string;
}

// 分页查询消费记录
export async function pageConsumeByKey(data: IPageConsumeByKey) {
  return http.get("/api/consume/pageConsumeByKey", {
    params: {
      ...data
    }
  });
}
