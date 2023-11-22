package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ew.gerocomium.common.constant.BedEnum;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.MarkEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.BuildingVo;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.BedMapper;
import com.ew.gerocomium.dao.mapper.BuildingMapper;
import com.ew.gerocomium.dao.mapper.FloorMapper;
import com.ew.gerocomium.dao.mapper.RoomMapper;
import com.ew.gerocomium.dao.po.*;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.dao.vo.*;
import com.ew.gerocomium.service.BuildService;
import com.ew.gerocomium.service.common.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BuildServiceImpl implements BuildService {
    @Resource
    private BuildingMapper buildingMapper;
    @Resource
    private BuildingFunc buildingFunc;
    @Resource
    private FloorMapper floorMapper;
    @Resource
    private FloorFunc floorFunc;
    @Resource
    private RoomTypeFunc roomTypeFunc;
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private RoomFunc roomFunc;
    @Resource
    private BedMapper bedMapper;
    @Resource
    private BedFunc bedFunc;
    @Resource
    private ElderFunc elderFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result getNoBedTreeAndPageBedByKey() {
        // 获取所有未被删除的楼栋
        List<Building> listNotDelBuilding = buildingFunc.listNotDelBuilding();
        // 获取所有未被删除的楼层
        List<Floor> listNotDelFloor = floorFunc.listNotDelFloor();
        // 获取所有未被删除的房间
        List<Room> listNotDelRoom = roomFunc.listNotDelRoom();
        // 生成楼栋树
        List<TreeVo> buildingTree = buildingFunc.generateBuildingSimpleTree(listNotDelBuilding, listNotDelFloor, listNotDelRoom, null, false);
//        List<BuildingVo> buildingTree = buildingFunc.generateBuildingTree(listNotDelBuilding, listNotDelFloor, listNotDelRoom, null, false);
        return Result.success(buildingTree);
    }

    @Override
    public Result pageBedByKey(PageBedByKeyQuery query) {
        // 获取所有未被删除的楼栋
        List<Building> listNotDelBuilding = buildingFunc.listNotDelBuilding();
        // 获取所有未被删除的楼层
        List<Floor> listNotDelFloor = floorFunc.listNotDelFloor();
        // 获取所有未被删除的房间
        List<Room> listNotDelRoom = roomFunc.listNotDelRoom();
        // 获取所有未被删除的床位
        List<Bed> listNotDelBed = bedFunc.listNotDelBed();
        // 根据条件过滤床位
        List<PageBedByKeyVo> pageBedByKeyVoList = bedFunc.filterBedByKey(listNotDelBuilding, listNotDelFloor, listNotDelRoom, listNotDelBed, query);
        // 封装返回数据
        PageResult<PageBedByKeyVo> pageResult = pageUtil.packPageResultData(pageBedByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result addBuilding(OperateBuildingQuery query) {
        // 判断楼栋是否已存在
        AssertUtil.isNull(buildingFunc.getBuildingByName(query.getName()), ExceptionEnum.BUILDING_REPEAT);
        // 判断楼栋是否已达到总数限制
        AssertUtil.notTrue(buildingFunc.checkBuildingTotal(), ExceptionEnum.BUILDING_OUT);
        // 初始化楼栋
        query.setId(null);
        Building building = BeanUtil.toBean(query, Building.class);
        building.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        buildingMapper.insert(building);
        return Result.success();
    }

    @Override
    public Result getBuildingById(Long buildingId) {
        // 根据编号获取楼栋
        Building building = buildingMapper.selectById(buildingId);
        // 判断是否为空
        AssertUtil.notNull(building, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(building, OperateBuildingVo.class));
    }

    @Override
    public Result editBuilding(OperateBuildingQuery query) {
        // 判断楼栋是否已存在
        Building buildingByName = buildingFunc.getBuildingByName(query.getName());
        boolean checkName = buildingByName != null && !Objects.equals(buildingByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.BUILDING_REPEAT);
        // 封装修改
        Building building = BeanUtil.toBean(query, Building.class);
        // 修改
        buildingMapper.updateById(building);
        return Result.success();
    }

    @Override
    public Result addFloor(OperateFloorQuery query) {
        // 判断楼层在该楼栋是否已存在
        AssertUtil.isNull(floorFunc.getFloorByName(query), ExceptionEnum.FLOOR_REPEAT);
        // 判断楼层是否已达到总数限制
        AssertUtil.notTrue(floorFunc.checkFloorTotal(query), ExceptionEnum.FLOOR_OUT);
        // 初始化楼层
        query.setId(null);
        Floor floor = BeanUtil.toBean(query, Floor.class);
        floor.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        floorMapper.insert(floor);
        return Result.success();
    }

    @Override
    public Result getFloorById(Long floorId) {
        // 根据编号获取楼层
        Floor floor = floorMapper.selectById(floorId);
        // 判断是否为空
        AssertUtil.notNull(floor, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(floor, OperateFloorVo.class));
    }

    @Override
    public Result editFloor(OperateFloorQuery query) {
        // 判断楼层在该楼栋是否已存在
        Floor floorByName = floorFunc.getFloorByName(query);
        boolean checkName = floorByName != null && !Objects.equals(floorByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.FLOOR_REPEAT);
        // 封装修改
        Floor floor = BeanUtil.toBean(query, Floor.class);
        // 修改
        floorMapper.updateById(floor);
        return Result.success();
    }

    @Override
    public Result listRoomType() {
        return Result.success(BeanUtil.copyToList(roomTypeFunc.listNotDelRoomType(null), OperateRoomTypeVo.class));
    }

    @Override
    public Result addRoom(OperateRoomQuery query) {
        // 判断房间在该楼层是否已存在
        AssertUtil.isNull(roomFunc.getRoomByName(query), ExceptionEnum.ROOM_REPEAT);
        // 判断楼层是否已达到总数限制
        AssertUtil.notTrue(roomFunc.checkRoomTotal(query), ExceptionEnum.ROOM_OUT);
        // 初始化楼层
        query.setId(null);
        Room room = BeanUtil.toBean(query, Room.class);
        room.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        roomMapper.insert(room);
        return Result.success();
    }

    @Override
    public Result getRoomById(Long roomId) {
        // 根据编号获取房间
        Room room = roomMapper.selectById(roomId);
        // 判断是否为空
        AssertUtil.notNull(room, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(room, OperateRoomVo.class));
    }

    @Override
    public Result editRoom(OperateRoomQuery query) {
        // 判断楼层在该楼栋是否已存在
        Room roomByName = roomFunc.getRoomByName(query);
        boolean checkName = roomByName != null && !Objects.equals(roomByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.ROOM_REPEAT);
        // 封装修改
        Room room = BeanUtil.toBean(query, Room.class);
        // 修改
        roomMapper.updateById(room);
        return Result.success();
    }

    @Override
    @Transactional
    public Result deleteNode(DeleteNodeQuery query) {
        Long id = query.getId();
        String mark = query.getMark();
        // 删除楼栋节点
        if (Objects.equals(mark, MarkEnum.BUILDING.getMark())) {
            buildingFunc.deleteBuildingNode(id);
            // 删除楼层节点
        } else if (Objects.equals(mark, MarkEnum.FLOOR.getMark())) {
            floorFunc.deleteFloorNode(null, id);
            // 删除房间节点
        } else if (Objects.equals(mark, MarkEnum.ROOM.getMark())) {
            roomFunc.deleteRoomNode(null, id);
            // 该节点标记不存在
        } else {
            AssertUtil.notTrue(true, ExceptionEnum.NODE_MARK_NOT_EXIST);
        }
        return Result.success();
    }

    @Override
    public Result addBed(OperateBedQuery query) {
        // 判断楼层在该楼栋是否已存在
        AssertUtil.isNull(bedFunc.getBedByName(query), ExceptionEnum.BED_REPEAT);
        // 判断楼层是否已达到总数限制
        AssertUtil.notTrue(bedFunc.checkBedTotal(query), ExceptionEnum.BED_OUT);
        // 初始化楼层
        query.setId(null);
        Bed bed = BeanUtil.toBean(query, Bed.class);
        bed.setDelFlag(YesNoEnum.NO.getCode());
        bed.setBedFlag(BedEnum.IDLE.getStatus());
        // 新增
        bedMapper.insert(bed);
        return Result.success();
    }

    @Override
    public Result getBedById(Long bedId) {
        // 根据编号获取床位
        Bed bed = bedMapper.selectById(bedId);
        // 判断是否为空
        AssertUtil.notNull(bed, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(bed, OperateBedVo.class));
    }

    @Override
    public Result editBed(OperateBedQuery query) {
        // 判断楼层在该楼栋是否已存在
        Bed bedByName = bedFunc.getBedByName(query);
        boolean checkName = bedByName != null && !Objects.equals(bedByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.BED_REPEAT);
        // 封装修改
        Bed bed = BeanUtil.toBean(query, Bed.class);
        // 修改
        bedMapper.updateById(bed);
        return Result.success();
    }

    @Override
    public Result deleteBed(Long bedId) {
        // 判断该床位是否被占用
        boolean checkBedFlag = !Objects.equals(bedMapper.selectById(bedId).getBedFlag(), BedEnum.IDLE.getStatus());
        AssertUtil.notTrue(checkBedFlag, ExceptionEnum.BED_NOT_IDLE);
        // 封装修改
        Bed bed = new Bed();
        bed.setId(bedId);
        bed.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        bedMapper.updateById(bed);
        return Result.success();
    }
}
