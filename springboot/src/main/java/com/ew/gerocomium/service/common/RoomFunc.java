package com.ew.gerocomium.service.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.BedEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.base.BuildingVo;
import com.ew.gerocomium.dao.mapper.RoomMapper;
import com.ew.gerocomium.dao.po.Bed;
import com.ew.gerocomium.dao.po.Elder;
import com.ew.gerocomium.dao.po.Room;
import com.ew.gerocomium.dao.query.OperateRoomQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 房间表公共方法
 */
@Component
public class RoomFunc {
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private BedFunc bedFunc;

    private static class FloorItem extends BuildingVo.BuildingItem.FloorItem {
    }

    private static class RoomItem extends BuildingVo.BuildingItem.FloorItem.RoomItem {
    }

    /**
     * 获取所有未被删除的房间
     *
     * @return
     */
    public List<Room> listNotDelRoom() {
        return roomMapper.selectList(new LambdaQueryWrapper<Room>()
                .eq(Room::getDelFlag, YesNoEnum.NO.getCode())
                .orderByDesc(Room::getCreateTime));
    }

    /**
     * 根据楼层编号获取所有未被删除的房间
     *
     * @param floorId
     * @return
     */
    public List<Room> listNotDelRoomByFloorId(Long floorId) {
        return roomMapper.selectList(new LambdaQueryWrapper<Room>()
                .eq(Room::getFloorId, floorId)
                .eq(Room::getDelFlag, YesNoEnum.NO.getCode())
                .orderByDesc(Room::getCreateTime));
    }

    /**
     * 根据楼层编号列表获取所有未被删除的房间
     *
     * @param floorIdList
     * @return
     */
    public List<Room> listNotDelRoomByFloorIdList(List<Long> floorIdList) {
        if (ObjUtil.isEmpty(floorIdList)) {
            return new ArrayList<>();
        } else {
            return roomMapper.selectList(new LambdaQueryWrapper<Room>()
                    .and(lambdaQueryWrapper ->
                            floorIdList.forEach(floorId -> lambdaQueryWrapper.eq(Room::getFloorId, floorId).or()))
                    .eq(Room::getDelFlag, YesNoEnum.NO.getCode())
                    .orderByDesc(Room::getCreateTime)
            );
        }
    }

    /**
     * 根据名称获取房间
     *
     * @return
     */
    public Room getRoomByName(OperateRoomQuery operateRoomQuery) {
        return roomMapper.selectOne(new LambdaQueryWrapper<Room>()
                .eq(Room::getFloorId, operateRoomQuery.getFloorId())
                .eq(Room::getName, operateRoomQuery.getName())
                .eq(Room::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 判断房间是否超过楼层总数限制
     *
     * @return
     */
    public Boolean checkRoomTotal(OperateRoomQuery operateRoomQuery) {
        Long roomTotal = roomMapper.selectCount(new LambdaQueryWrapper<Room>()
                .eq(Room::getFloorId, operateRoomQuery.getFloorId())
                .eq(Room::getDelFlag, YesNoEnum.NO.getCode()));
        return roomTotal >= operateRoomQuery.getRoomLimit();
    }

    /**
     * 获取空闲房间
     *
     * @param notDelRoomList
     * @param notDelBedList
     * @return
     */
    public List<Room> listIdleRoom(List<Room> notDelRoomList, List<Bed> notDelBedList) {
        return notDelRoomList.stream()
                .filter(room -> {
                    // 验证房间内是否有床位
                    boolean checkBedNotNull = false;
                    // 验证房间内床位是否空闲
                    boolean checkBedIsIdle = true;
                    for (Bed bed : notDelBedList) {
                        // 该房间床位
                        if (Objects.equals(room.getId(), bed.getRoomId())) {
                            // 该房间有床位
                            checkBedNotNull = true;
                            // 该房间有床位被占用
                            if (!Objects.equals(bed.getBedFlag(), BedEnum.IDLE.getStatus())) {
                                checkBedIsIdle = false;
                                break;
                            }
                        }
                    }
                    return checkBedNotNull && checkBedIsIdle;
                })
                .collect(Collectors.toList());
    }

    /**
     * 生成房间-床位树(无床位的房间不会显示)
     *
     * @param roomList
     * @param bedList
     * @param elderList
     * @param elderNameFlag
     * @return
     */
    public List<BuildingVo.BuildingItem.FloorItem> generateRoomTree(List<Room> roomList, List<Bed> bedList, List<Elder> elderList, Boolean elderNameFlag) {
        // 封装房间
        List<BuildingVo.BuildingItem.FloorItem> roomVoList = new ArrayList<>();
        roomList.forEach(room -> {
            // 房间
            FloorItem roomVo = BeanUtil.toBean(room, FloorItem.class);
            // 封装床位
            List<BuildingVo.BuildingItem.FloorItem.RoomItem> bedVoList = new ArrayList<>();
            bedList.forEach(bed -> {
                if (Objects.equals(roomVo.getId(), bed.getRoomId())) {
                    // 床位
                    RoomItem bedVo = BeanUtil.toBean(bed, RoomItem.class);
                    // 封装床位老人信息
                    elderList.forEach(elder -> {
                        boolean existFlag = false;
                        if (Objects.equals(elder.getBedId(), bed.getId())) {
                            existFlag = true;
                            bedVo.setElderName(elder.getName());
                            bedVo.setSex(elder.getSex());
                            bedVo.setAge(elder.getAge());
                        }
                        // ((elderNameFlag && existFlag) || (!elderNameFlag && existFlag)) && !bedVoList.contains(bedVo)
                        // ((搜索关键字老人姓名不为空 && 该床位有老人) || (搜索关键字老人姓名为空)) && !bedVoList.contains(bedVo)
                        boolean addFlag = (!elderNameFlag || existFlag) && !bedVoList.contains(bedVo);
                        if (addFlag) {
                            bedVoList.add(bedVo);
                        }
                    });
                }
            });
            if (ObjUtil.isNotEmpty(bedVoList)) {
                roomVo.setBedList(bedVoList);
                roomVoList.add(roomVo);
            }
        });
        return roomVoList;
    }

    /**
     * 删除房间节点
     *
     * @param floorId
     * @param roomId
     */
    public void deleteRoomNode(Long floorId, Long roomId) {
        // 删除楼层节点后
        if (ObjUtil.isNotEmpty(floorId)) {
            // 根据楼层编号获取所有未被删除的房间
            List<Room> roomList = listNotDelRoomByFloorId(floorId);
            roomList.forEach(room -> {
                // 封装修改
                room.setDelFlag(YesNoEnum.YES.getCode());
                // 修改
                roomMapper.updateById(room);
                // 删除床位节点
                bedFunc.deleteBedNode(room.getId());
            });
            // 直接删除床位节点
        } else if (ObjUtil.isNotEmpty(roomId)) {
            // 封装修改
            Room room = new Room();
            room.setId(roomId);
            room.setDelFlag(YesNoEnum.YES.getCode());
            // 修改
            roomMapper.updateById(room);
            // 删除床位节点
            bedFunc.deleteBedNode(room.getId());
        }
    }
}
