package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.ew.gerocomium.dao.base.BuildingVo;
import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.po.Bed;
import com.ew.gerocomium.dao.po.Elder;
import com.ew.gerocomium.dao.po.Floor;
import com.ew.gerocomium.dao.po.Room;
import com.ew.gerocomium.dao.query.ListRoomByKeyQuery;
import com.ew.gerocomium.service.BedPanoramaService;
import com.ew.gerocomium.service.common.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BedPanoramaServiceImpl implements BedPanoramaService {
    @Resource
    private BuildingFunc buildingFunc;
    @Resource
    private FloorFunc floorFunc;
    @Resource
    private RoomFunc roomFunc;
    @Resource
    private BedFunc bedFunc;
    @Resource
    private ElderFunc elderFunc;

    @Override
    public Result listBuilding() {
        return Result.success(BeanUtil.copyToList(buildingFunc.listNotDelBuilding(), DropDown.class));
    }

    @Override
    public Result listFloorByBuildingId(Long buildingId) {
        return Result.success(BeanUtil.copyToList(floorFunc.listNotDelFloorByBuildingId(buildingId), DropDown.class));
    }

    @Override
    public Result listRoomByKey(ListRoomByKeyQuery query) {
        // 获取楼层编号列表
        List<Long> floorIdList = new ArrayList<>();
        for (Floor floor : ObjUtil.isEmpty(query.getBuildingId()) ?
                floorFunc.listNotDelFloor() :
                floorFunc.listNotDelFloorByBuildingId(query.getBuildingId())) {
            floorIdList.add(floor.getId());
        }
        // 过滤楼层编号列表
        floorIdList = ObjUtil.isEmpty(query.getFloorId()) ?
                floorIdList :
                floorIdList.stream().filter(floorId -> Objects.equals(floorId, query.getFloorId())).collect(Collectors.toList());
        // 根据楼层编号获取所有未被删除的房间
        List<Room> listNotDelRoomByFloorId = roomFunc.listNotDelRoomByFloorIdList(floorIdList);
        // 获取所有未被删除的床位
        List<Bed> listNotDelBed = bedFunc.listNotDelBed();
        // 根据老人姓名获取所有占用房间的老人
        List<Elder> listLiveElder = elderFunc.listLiveElder(query.getElderName());
        // 生成房间-床位树
        List<BuildingVo.BuildingItem.FloorItem> roomTree = roomFunc.generateRoomTree(listNotDelRoomByFloorId, listNotDelBed, listLiveElder, ObjUtil.isNotEmpty(query.getElderName()));
        return Result.success(roomTree);
    }
}
