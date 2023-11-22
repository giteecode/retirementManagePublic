package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.WarehouseMapper;
import com.ew.gerocomium.dao.mapper.WarehouseMaterialMapper;
import com.ew.gerocomium.dao.po.Warehouse;
import com.ew.gerocomium.dao.query.OperateWarehouseQuery;
import com.ew.gerocomium.dao.query.PageWarehouseByKeyQuery;
import com.ew.gerocomium.dao.vo.OperateWarehouseVo;
import com.ew.gerocomium.dao.vo.PageWarehouseByKeyVo;
import com.ew.gerocomium.service.WarehouseService;
import com.ew.gerocomium.service.common.StaffFunc;
import com.ew.gerocomium.service.common.WarehouseFunc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Resource
    private WarehouseMapper warehouseMapper;
    @Resource
    private WarehouseFunc warehouseFunc;
    @Resource
    private WarehouseMaterialMapper warehouseMaterialMapper;
    @Resource
    private StaffFunc staffFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageWarehouseByKey(PageWarehouseByKeyQuery query) {
        // 根据关键字获取仓库列表
        List<PageWarehouseByKeyVo> pageWarehouseByKeyVoList = warehouseMapper.listWarehouseByKey(query);
        // 封装返回数据
        PageResult<PageWarehouseByKeyVo> pageResult = pageUtil.packPageResultData(pageWarehouseByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result listWarehouseStaff() {
        return Result.success(BeanUtil.copyToList(staffFunc.listStaffByRoleId(6L), DropDown.class));
    }

    @Override
    public Result addWarehouse(OperateWarehouseQuery query) {
        // 验证仓库是否存在
        AssertUtil.isNull(warehouseFunc.getWarehouseByName(query.getName()), ExceptionEnum.WAREHOUSE_REPEAT);
        // 初始化仓库
        query.setId(null);
        Warehouse warehouse = BeanUtil.toBean(query, Warehouse.class);
        warehouse.setDelFlag(YesNoEnum.NO.getCode());
        // 新增仓库
        warehouseMapper.insert(warehouse);
        return Result.success();
    }

    @Override
    public Result getWarehouseById(Long warehouseId) {
        // 根据编号获取仓库
        Warehouse warehouse = warehouseMapper.selectById(warehouseId);
        // 判断是否为空
        AssertUtil.notNull(warehouse, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(warehouse, OperateWarehouseVo.class));
    }

    @Override
    public Result editWarehouse(OperateWarehouseQuery query) {
        // 验证仓库是否存在
        Warehouse warehouseByName = warehouseFunc.getWarehouseByName(query.getName());
        boolean checkName = warehouseByName != null && !Objects.equals(warehouseByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.WAREHOUSE_REPEAT);
        // 封装修改
        Warehouse warehouse = BeanUtil.toBean(query, Warehouse.class);
        // 修改
        warehouseMapper.updateById(warehouse);
        return Result.success();
    }

    @Override
    public Result deleteWarehouse(Long warehouseId) {
        // 验证仓库是否存在物资
        Long materialTotalNum = warehouseMaterialMapper.sumWarehouseMaterialNumByWarehouseId(warehouseId);
        boolean checkTotal = materialTotalNum != null && materialTotalNum > 0;
        AssertUtil.notTrue(checkTotal, ExceptionEnum.WAREHOUSE_NOT_NULL);
        // 封装修改
        Warehouse warehouse = new Warehouse();
        warehouse.setId(warehouseId);
        warehouse.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        warehouseMapper.updateById(warehouse);
        return Result.success();
    }
}
