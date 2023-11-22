package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;

public interface BuildService {
    /**
     * 获取楼栋-楼层-房间树和分页查询床位
     *
     * @return
     */
    Result getNoBedTreeAndPageBedByKey();

    /**
     * 分页查询床位
     *
     * @return
     */
    Result pageBedByKey(PageBedByKeyQuery pageBedByKeyQuery);

    /**
     * 新增楼栋
     *
     * @param operateBuildingQuery
     * @return
     */
    Result addBuilding(OperateBuildingQuery operateBuildingQuery);

    /**
     * 根据编号获取楼栋
     *
     * @param buildingId
     * @return
     */
    Result getBuildingById(Long buildingId);

    /**
     * 编辑楼栋
     *
     * @param operateBuildingQuery
     * @return
     */
    Result editBuilding(OperateBuildingQuery operateBuildingQuery);

    /**
     * 新增楼层
     *
     * @param operateFloorQuery
     * @return
     */
    Result addFloor(OperateFloorQuery operateFloorQuery);

    /**
     * 根据编号获取楼层
     *
     * @param floorId
     * @return
     */
    Result getFloorById(Long floorId);

    /**
     * 编辑楼层
     *
     * @param operateFloorQuery
     * @return
     */
    Result editFloor(OperateFloorQuery operateFloorQuery);

    /**
     * 获取房间类型列表
     *
     * @return
     */
    Result listRoomType();

    /**
     * 新增房间
     *
     * @param operateRoomQuery
     * @return
     */
    Result addRoom(OperateRoomQuery operateRoomQuery);

    /**
     * 根据编号获取房间
     *
     * @param roomId
     * @return
     */
    Result getRoomById(Long roomId);

    /**
     * 编辑房间
     *
     * @param operateRoomQuery
     * @return
     */
    Result editRoom(OperateRoomQuery operateRoomQuery);

    /**
     * 删除节点
     *
     * @param deleteNodeQuery
     * @return
     */
    Result deleteNode(DeleteNodeQuery deleteNodeQuery);

    /**
     * 新增床位
     *
     * @param operateBedQuery
     * @return
     */
    Result addBed(OperateBedQuery operateBedQuery);

    /**
     * 根据编号获取床位
     *
     * @param bedId
     * @return
     */
    Result getBedById(Long bedId);

    /**
     * 编辑床位
     *
     * @param operateBedQuery
     * @return
     */
    Result editBed(OperateBedQuery operateBedQuery);

    /**
     * 删除床位
     *
     * @param bedId
     * @return
     */
    Result deleteBed(Long bedId);
}
