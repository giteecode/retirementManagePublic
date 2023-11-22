package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.RoomTypeMapper;
import com.ew.gerocomium.dao.po.RoomType;
import com.ew.gerocomium.dao.query.OperateRoomTypeQuery;
import com.ew.gerocomium.dao.query.PageRoomTypeByKeyQuery;
import com.ew.gerocomium.dao.vo.OperateRoomTypeVo;
import com.ew.gerocomium.dao.vo.PageRoomTypeByKeyVo;
import com.ew.gerocomium.service.RoomTypeService;
import com.ew.gerocomium.service.common.RoomTypeFunc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Resource
    private RoomTypeMapper roomTypeMapper;
    @Resource
    private RoomTypeFunc roomTypeFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageRoomTypeByKey(PageRoomTypeByKeyQuery query) {
        // 根据关键字获取所有未被删除的房间类型
        List<RoomType> roomTypeList = roomTypeFunc.listNotDelRoomType(query.getRoomTypeName());
        // 列表实体转换
        List<PageRoomTypeByKeyVo> pageRoomTypeByKeyVoList = BeanUtil.copyToList(roomTypeList, PageRoomTypeByKeyVo.class);
        // 封装返回数据
        PageResult<PageRoomTypeByKeyVo> pageResult = pageUtil.packPageResultData(pageRoomTypeByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result addRoomType(OperateRoomTypeQuery query) {
        // 判断房间类型是否已存在
        AssertUtil.isNull(roomTypeFunc.getRoomTypeByName(query.getName()), ExceptionEnum.ROOM_TYPE_REPEAT);
        // 初始化房间类型
        RoomType roomType = new RoomType();
        roomType.setName(query.getName());
        roomType.setMonthPrice(query.getMonthPrice());
        roomType.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        roomTypeMapper.insert(roomType);
        return Result.success();
    }

    @Override
    public Result getRoomTypeById(Long roomTypeId) {
        // 根据编号获取房间类型
        RoomType roomType = roomTypeMapper.selectById(roomTypeId);
        // 判断是否为空
        AssertUtil.notNull(roomType, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(roomType, OperateRoomTypeVo.class));
    }

    @Override
    public Result editRoomType(OperateRoomTypeQuery query) {
        // 判断房间类型是否已存在
        RoomType roomTypeByName = roomTypeFunc.getRoomTypeByName(query.getName());
        boolean checkName = roomTypeByName != null && !Objects.equals(roomTypeByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.ROOM_TYPE_REPEAT);
        // 封装修改
        RoomType roomType = new RoomType();
        roomType.setId(query.getId());
        roomType.setName(query.getName());
        roomType.setMonthPrice(query.getMonthPrice());
        // 修改
        roomTypeMapper.updateById(roomType);
        return Result.success();
    }

    @Override
    public Result deleteRoomType(Long roomTypeId) {
        // 封装修改
        RoomType roomType = new RoomType();
        roomType.setId(roomTypeId);
        roomType.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        roomTypeMapper.updateById(roomType);
        return Result.success();
    }
}
