package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.ListRoomByKeyQuery;

public interface BedPanoramaService {
    /**
     * 获取楼栋列表
     *
     * @return
     */
    Result listBuilding();

    /**
     * 获取楼层列表
     *
     * @param buildingId
     * @return
     */
    Result listFloorByBuildingId(Long buildingId);

    /**
     * 获取房间列表
     *
     * @param listRoomByKeyQuery
     * @return
     */
    Result listRoomByKey(ListRoomByKeyQuery listRoomByKeyQuery);
}
