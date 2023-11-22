package com.ew.gerocomium.service.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.gerocomium.common.constant.BedEnum;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.dao.mapper.BedMapper;
import com.ew.gerocomium.dao.po.*;
import com.ew.gerocomium.dao.query.OperateBedQuery;
import com.ew.gerocomium.dao.query.OperateRoomQuery;
import com.ew.gerocomium.dao.query.PageBedByKeyQuery;
import com.ew.gerocomium.dao.vo.PageBedByKeyVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 床位表公共方法
 */
@Component
public class BedFunc extends ServiceImpl<BedMapper, Bed> {
    @Resource
    private BedMapper bedMapper;

    /**
     * 根据编号获取床位
     *
     * @param bedId
     */
    public Bed getBedById(Long bedId) {
        return bedMapper.selectOne(new LambdaQueryWrapper<Bed>()
                .eq(Bed::getId, bedId)
                .eq(Bed::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 获取所有未被删除的床位
     *
     * @return
     */
    public List<Bed> listNotDelBed() {
        return bedMapper.selectList(new LambdaQueryWrapper<Bed>()
                .eq(Bed::getDelFlag, YesNoEnum.NO.getCode())
                .orderByDesc(Bed::getCreateTime));
    }

    /**
     * 获取所有空闲床位
     *
     * @return
     */
    public List<Bed> listIdleBed() {
        return bedMapper.selectList(new LambdaQueryWrapper<Bed>()
                .eq(Bed::getBedFlag, BedEnum.IDLE.getStatus())
                .eq(Bed::getDelFlag, YesNoEnum.NO.getCode())
                .orderByDesc(Bed::getCreateTime));
    }

    /**
     * 根据名称获取床位
     *
     * @return
     */
    public Bed getBedByName(OperateBedQuery operateBedQuery) {
        return bedMapper.selectOne(new LambdaQueryWrapper<Bed>()
                .eq(Bed::getRoomId, operateBedQuery.getRoomId())
                .eq(Bed::getName, operateBedQuery.getName())
                .eq(Bed::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 判断床位是否超过房间总数限制
     *
     * @return
     */
    public Boolean checkBedTotal(OperateBedQuery operateBedQuery) {
        Long roomTotal = bedMapper.selectCount(new LambdaQueryWrapper<Bed>()
                .eq(Bed::getRoomId, operateBedQuery.getRoomId())
                .eq(Bed::getDelFlag, YesNoEnum.NO.getCode()));
        return roomTotal >= operateBedQuery.getBedLimit();
    }

    /**
     * 获取空闲床位
     *
     * @return
     */
    public List<Bed> listIdleBed(List<Bed> notDelBedList) {
        return notDelBedList.stream()
                .filter(bed -> Objects.equals(bed.getBedFlag(), BedEnum.IDLE.getStatus()))
                .collect(Collectors.toList());
    }

    /**
     * 获取已登记退床
     *
     * @return
     */
    public List<Bed> listExitAuditBed(List<Bed> notDelBedList) {
        return notDelBedList.stream()
                .filter(bed -> Objects.equals(bed.getBedFlag(), BedEnum.EXIT_AUDIT.getStatus()))
                .collect(Collectors.toList());
    }

    /**
     * 根据条件过滤床位
     *
     * @param buildingList
     * @param floorList
     * @param roomList
     * @param bedList
     * @param query
     * @return
     */
    public List<PageBedByKeyVo> filterBedByKey(List<Building> buildingList,
                                               List<Floor> floorList,
                                               List<Room> roomList,
                                               List<Bed> bedList,
                                               PageBedByKeyQuery query) {
        // 过滤楼栋
        if (ObjUtil.isNotEmpty(query.getBuildId())) {
            buildingList = buildingList.stream()
                    .filter(building -> Objects.equals(building.getId(), query.getBuildId()))
                    .collect(Collectors.toList());
        }
        // 过滤楼层
        if (ObjUtil.isNotEmpty(query.getFloorId())) {
            floorList = floorList.stream()
                    .filter(floor -> Objects.equals(floor.getId(), query.getFloorId()))
                    .collect(Collectors.toList());
        }
        // 过滤房间
        if (ObjUtil.isNotEmpty(query.getRoomId())) {
            roomList = roomList.stream()
                    .filter(room -> Objects.equals(room.getId(), query.getRoomId()))
                    .collect(Collectors.toList());
        }
        // 过滤床位
        if (ObjUtil.isNotEmpty(query.getBedFlag())) {
            bedList = bedList.stream()
                    .filter(bed -> Objects.equals(bed.getBedFlag(), query.getBedFlag()))
                    .collect(Collectors.toList());
        }
        // 封装返回数据
        List<PageBedByKeyVo> pageBedByKeyVoList = new ArrayList<>();
        // 从楼栋开始
        for (Building building : buildingList) {
            for (Floor floor : floorList) {
                // 该楼栋有楼层
                if(Objects.equals(floor.getBuildingId(),building.getId())){
                    for (Room room : roomList) {
                        // 该楼层有房间
                        if(Objects.equals(room.getFloorId(),floor.getId())){
                            for (Bed bed : bedList) {
                                // 该房间有床位
                                if(Objects.equals(bed.getRoomId(),room.getId())){
                                    pageBedByKeyVoList.add(BeanUtil.toBean(bed,PageBedByKeyVo.class));
                                }
                            }
                        }
                    }
                }
            }
        }

        // 做全局final处理
//        List<Building> finalBuildingList = buildingList;
//        List<Floor> finalFloorList = floorList;
//        List<Room> finalRoomList = roomList;
        // 床位
//        bedList.forEach(bed -> {
//            // 实例化Vo
//            PageBedByKeyVo pageBedByKeyVo = new PageBedByKeyVo();
//            // 设置床位编号
//            pageBedByKeyVo.setId(bed.getId());
//            // 设置床位名称
//            pageBedByKeyVo.setBedName(bed.getName());
//            // 设置床位状态
//            pageBedByKeyVo.setBedFlag(bed.getBedFlag());
//            // 不属于空闲状态的床位才有老人
//            if (!Objects.equals(bed.getBedFlag(), BedEnum.IDLE.getStatus())) {
//                // 老人
//                elderList.forEach(elder -> {
//                    if (Objects.equals(elder.getBedId(), bed.getId())) {
//                        // 设置所住老人
//                        pageBedByKeyVo.setElderName(elder.getName());
//                    }
//                });
//            }
//            // 房间
//            finalRoomList.forEach(room -> {
//                if (Objects.equals(room.getId(), bed.getRoomId())) {
//                    // 设置房间名称
//                    pageBedByKeyVo.setRoomName(room.getName());
//                    // 楼层
//                    finalFloorList.forEach(floor -> {
//                        if (Objects.equals(floor.getId(), room.getFloorId())) {
//                            // 设置楼层名称
//                            pageBedByKeyVo.setFloorName(floor.getName());
//                            // 楼栋
//                            finalBuildingList.forEach(building -> {
//                                if (Objects.equals(building.getId(), floor.getBuildingId())) {
//                                    // 设置楼栋名称
//                                    pageBedByKeyVo.setBuildingName(building.getName());
//                                }
//                            });
//                        }
//                    });
//                }
//            });
//            pageBedByKeyVoList.add(pageBedByKeyVo);
//        });
        return pageBedByKeyVoList;
    }

    /**
     * 判断床位是否被占用
     *
     * @param bedId
     */
    public void checkBedOccupy(Long bedId) {
        // 根据编号获取床位
        Bed bed = bedMapper.selectOne(new LambdaQueryWrapper<Bed>()
                .eq(Bed::getId, bedId)
                .eq(Bed::getDelFlag, YesNoEnum.NO.getCode()));
        // 验证床位是否存在
        AssertUtil.notNull(bed, ExceptionEnum.BED_NULL);
        // 验证床位是否被占用
        AssertUtil.isTrue(Objects.equals(bed.getBedFlag(), BedEnum.IDLE.getStatus()), ExceptionEnum.BED_OCCUPY);
    }

    /**
     * 删除床位节点
     *
     * @param roomId
     */
    public void deleteBedNode(Long roomId) {
        // 获取该房间所有床位
        List<Bed> bedList = bedMapper.selectList(new LambdaQueryWrapper<Bed>()
                .eq(Bed::getRoomId, roomId)
                .eq(Bed::getDelFlag, YesNoEnum.NO.getCode()));
        bedList.forEach(bed -> {
            // 判断该床位是否被占用
            AssertUtil.notTrue(!Objects.equals(bed.getBedFlag(), BedEnum.IDLE.getStatus()), ExceptionEnum.NODE_BED_NOT_IDLE);
            // 封装修改
            bed.setDelFlag(YesNoEnum.YES.getCode());
            // 修改
            bedMapper.updateById(bed);
        });
    }
}
