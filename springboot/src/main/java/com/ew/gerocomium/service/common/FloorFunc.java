package com.ew.gerocomium.service.common;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.mapper.FloorMapper;
import com.ew.gerocomium.dao.po.Floor;
import com.ew.gerocomium.dao.query.OperateFloorQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 楼层表公共方法
 */
@Component
public class FloorFunc {
    @Resource
    private FloorMapper floorMapper;
    @Resource
    private RoomFunc roomFunc;

    /**
     * 获取所有未被删除的楼层
     *
     * @return
     */
    public List<Floor> listNotDelFloor() {
        return floorMapper.selectList(new LambdaQueryWrapper<Floor>()
                .eq(Floor::getDelFlag, YesNoEnum.NO.getCode())
                .orderByDesc(Floor::getCreateTime));
    }

    /**
     * 根据楼栋编号获取所有未被删除的楼层
     *
     * @param buildingId
     * @return
     */
    public List<Floor> listNotDelFloorByBuildingId(Long buildingId) {
        LambdaQueryWrapper<Floor> floorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        floorLambdaQueryWrapper
                .eq(Floor::getDelFlag, YesNoEnum.NO.getCode())
                .orderByDesc(Floor::getCreateTime);
        if (ObjUtil.isNotEmpty(buildingId)) {
            floorLambdaQueryWrapper.eq(Floor::getBuildingId, buildingId);
        }
        return floorMapper.selectList(floorLambdaQueryWrapper);
    }

    /**
     * 根据名称获取楼层
     *
     * @return
     */
    public Floor getFloorByName(OperateFloorQuery operateFloorQuery) {
        return floorMapper.selectOne(new LambdaQueryWrapper<Floor>()
                .eq(Floor::getBuildingId, operateFloorQuery.getBuildingId())
                .eq(Floor::getName, operateFloorQuery.getName())
                .eq(Floor::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 判断楼层是否超过楼栋总数限制
     *
     * @return
     */
    public Boolean checkFloorTotal(OperateFloorQuery operateFloorQuery) {
        Long floorTotal = floorMapper.selectCount(new LambdaQueryWrapper<Floor>()
                .eq(Floor::getBuildingId, operateFloorQuery.getBuildingId())
                .eq(Floor::getDelFlag, YesNoEnum.NO.getCode()));
        return floorTotal >= operateFloorQuery.getFloorLimit();
    }

    /**
     * 删除楼层节点
     *
     * @param buildingId
     * @param floorId
     */
    public void deleteFloorNode(Long buildingId, Long floorId) {
        // 删除楼栋节点后
        if (ObjUtil.isNotEmpty(buildingId)) {
            // 根据楼栋编号获取所有未被删除的楼层
            List<Floor> floorList = listNotDelFloorByBuildingId(buildingId);
            floorList.forEach(floor -> {
                // 封装修改
                floor.setDelFlag(YesNoEnum.YES.getCode());
                // 修改
                floorMapper.updateById(floor);
                // 删除房间节点
                roomFunc.deleteRoomNode(floor.getId(), null);
            });
            // 直接删除楼层节点
        } else if (ObjUtil.isNotEmpty(floorId)) {
            // 封装修改
            Floor floor = new Floor();
            floor.setId(floorId);
            floor.setDelFlag(YesNoEnum.YES.getCode());
            // 修改
            floorMapper.updateById(floor);
            // 删除房间节点
            roomFunc.deleteRoomNode(floor.getId(), null);
        }
    }
}
