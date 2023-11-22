package com.ew.gerocomium.service.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.base.BuildingVo;
import com.ew.gerocomium.dao.mapper.BuildingMapper;
import com.ew.gerocomium.dao.po.*;
import com.ew.gerocomium.dao.vo.TreeVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 楼栋表公共方法
 */
@Component
public class BuildingFunc {
    @Resource
    private BuildingMapper buildingMapper;
    @Resource
    private FloorFunc floorFunc;

    private static class BuildingItem extends BuildingVo.BuildingItem {
    }

    private static class FloorItem extends BuildingVo.BuildingItem.FloorItem {
    }

    private static class RoomItem extends BuildingVo.BuildingItem.FloorItem.RoomItem {
    }

    /**
     * 获取所有未被删除的楼栋
     *
     * @return
     */
    public List<Building> listNotDelBuilding() {
        return buildingMapper.selectList(new LambdaQueryWrapper<Building>()
                .eq(Building::getDelFlag, YesNoEnum.NO.getCode())
                .orderByDesc(Building::getCreateTime));
    }

    /**
     * 根据名称获取楼栋
     *
     * @return
     */
    public Building getBuildingByName(String buildingName) {
        return buildingMapper.selectOne(new LambdaQueryWrapper<Building>()
                .eq(Building::getName, buildingName)
                .eq(Building::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 判断楼栋是否超过总数限制
     *
     * @return
     */
    public Boolean checkBuildingTotal() {
        Long buildingTotal = buildingMapper.selectCount(new LambdaQueryWrapper<Building>()
                .eq(Building::getDelFlag, YesNoEnum.NO.getCode()));
        return buildingTotal >= Constant.TOTAL_LIMIT;

    }

    /**
     * 生成楼栋树
     *
     * @param buildingList
     * @param floorList
     * @param roomList
     * @param bedList
     * @param needBed
     * @return
     */
    public List<BuildingVo> generateBuildingTree(List<Building> buildingList,
                                                 List<Floor> floorList,
                                                 List<Room> roomList,
                                                 List<Bed> bedList,
                                                 Boolean needBed) {
        // 封装楼栋
        List<BuildingVo> buildingVoList = new ArrayList<>();
        buildingList.forEach(building -> {
            // 楼栋
            BuildingVo buildingVo = BeanUtil.toBean(building, BuildingVo.class);
            // 封装楼层
            List<BuildingVo.BuildingItem> floorVoList = new ArrayList<>();
            floorList.forEach(floor -> {
                if (Objects.equals(building.getId(), floor.getBuildingId())) {
                    // 楼层
                    BuildingItem floorVo = BeanUtil.toBean(floor, BuildingItem.class);
                    // 封装房间
                    List<BuildingVo.BuildingItem.FloorItem> roomVoList = new ArrayList<>();
                    roomList.forEach(room -> {
                        if (Objects.equals(floor.getId(), room.getFloorId())) {
                            // 房间
                            FloorItem roomVo = BeanUtil.toBean(room, FloorItem.class);
                            if (needBed) {
                                // 封装床位
                                List<BuildingVo.BuildingItem.FloorItem.RoomItem> bedVoList = new ArrayList<>();
                                bedList.forEach(bed -> {
                                    if (Objects.equals(roomVo.getId(), bed.getRoomId())) {
                                        // 床位
                                        RoomItem bedVo = BeanUtil.toBean(bed, RoomItem.class);
                                        bedVoList.add(bedVo);
                                    }
                                });
                                roomVo.setBedList(bedVoList);
                            }
                            roomVoList.add(roomVo);
                        }
                    });
                    floorVo.setRoomList(roomVoList);
                    floorVoList.add(floorVo);
                }
            });
            buildingVo.setFloorList(floorVoList);
            buildingVoList.add(buildingVo);
        });
        return buildingVoList;
    }

    /**
     * 生成楼栋树
     *
     * @param buildingList
     * @param floorList
     * @param roomList
     * @param bedList
     * @param needBed
     * @return
     */
    public List<TreeVo> generateBuildingSimpleTree(List<Building> buildingList,
                                                   List<Floor> floorList,
                                                   List<Room> roomList,
                                                   List<Bed> bedList,
                                                   Boolean needBed) {
        // 封装楼栋
        List<TreeVo> buildingVoList = new ArrayList<>();
        buildingList.forEach(building -> {
            // 楼栋
            TreeVo buildingVo = BeanUtil.toBean(building, TreeVo.class);
            buildingVo.setLevel(1);
            buildingVo.setBuildId(building.getId());
            buildingVo.setFloorLimit(building.getFloorNum());
            // 封装楼层
            List<TreeVo> floorVoList = new ArrayList<>();
            floorList.forEach(floor -> {
                if (Objects.equals(building.getId(), floor.getBuildingId())) {
                    // 楼层
                    TreeVo floorVo = BeanUtil.toBean(floor, TreeVo.class);
                    floorVo.setLevel(2);
                    floorVo.setFloorId(floor.getId());
                    floorVo.setRoomLimit(floor.getRoomNum());
                    // 封装房间
                    List<TreeVo> roomVoList = new ArrayList<>();
                    roomList.forEach(room -> {
                        if (Objects.equals(floor.getId(), room.getFloorId())) {
                            // 房间
                            TreeVo roomVo = BeanUtil.toBean(room, TreeVo.class);
                            roomVo.setLevel(3);
                            roomVo.setRoomId(room.getId());
                            roomVo.setBedLimit(room.getBedNum());
                            if (needBed) {
                                // 封装床位
                                List<TreeVo> bedVoList = new ArrayList<>();
                                bedList.forEach(bed -> {
                                    if (Objects.equals(roomVo.getId(), bed.getRoomId())) {
                                        // 床位
                                        TreeVo bedVo = BeanUtil.toBean(bed, TreeVo.class);
                                        bedVo.setLevel(4);
                                        bedVoList.add(bedVo);
                                    }
                                });
                                roomVo.setChildrenList(bedVoList);
                            }
                            roomVoList.add(roomVo);
                        }
                    });
                    floorVo.setChildrenList(roomVoList);
                    floorVoList.add(floorVo);
                }
            });
            buildingVo.setChildrenList(floorVoList);
            buildingVoList.add(buildingVo);
        });
        // 倒序
        Collections.reverse(buildingVoList);
        // 生成唯一标识
        int[] i = {0};
        buildingVoList.forEach(node -> generateOnlyMark(node, i));
        return buildingVoList;
    }

    // 生成唯一标识
    private void generateOnlyMark(TreeVo node, int[] i) {
        node.setOnlyMark(i[0]++);
        if (ObjUtil.isNotEmpty(node.getChildrenList())) {
            for (TreeVo child : node.getChildrenList()) {
                generateOnlyMark(child, i);
            }
        }
    }

    /**
     * 删除楼栋节点
     *
     * @param buildingId
     */
    public void deleteBuildingNode(Long buildingId) {
        // 封装修改
        Building building = new Building();
        building.setId(buildingId);
        building.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        buildingMapper.updateById(building);
        // 删除楼层节点
        floorFunc.deleteFloorNode(buildingId, null);
    }
}
