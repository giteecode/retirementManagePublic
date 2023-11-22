package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.ew.gerocomium.common.constant.AuditEnum;
import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.CommonUtil;
import com.ew.gerocomium.common.util.DateUtilWen;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.WarehouseMaterialMapper;
import com.ew.gerocomium.dao.mapper.WarehouseRecordMapper;
import com.ew.gerocomium.dao.po.WarehouseMaterial;
import com.ew.gerocomium.dao.po.WarehouseRecord;
import com.ew.gerocomium.dao.query.AddWarehouseRecordQuery;
import com.ew.gerocomium.dao.query.AuditWarehouseRecordQuery;
import com.ew.gerocomium.dao.query.PageWarehouseRecordByKeyQuery;
import com.ew.gerocomium.dao.vo.GetWarehouseRecordByIdVo;
import com.ew.gerocomium.dao.vo.PageWarehouseRecordByKeyVo;
import com.ew.gerocomium.service.WarehouseRecordService;
import com.ew.gerocomium.service.common.CommonFunc;
import com.ew.gerocomium.service.common.WarehouseFunc;
import com.ew.gerocomium.service.common.WarehouseMaterialFunc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WarehouseRecordServiceImpl implements WarehouseRecordService {
    @Resource
    private WarehouseFunc warehouseFunc;
    @Resource
    private WarehouseRecordMapper warehouseRecordMapper;
    @Resource
    private WarehouseMaterialMapper warehouseMaterialMapper;
    @Resource
    private WarehouseMaterialFunc warehouseMaterialFunc;
    @Resource
    private CommonFunc commonFunc;
    @Resource
    private CommonUtil commonUtil;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageWarehouseRecordByKey(PageWarehouseRecordByKeyQuery query) {
        String materialName = query.getMaterialName();
        // 获取开始/结束时间
        Date startTime = DateUtilWen.getDayStartTime(DateUtilWen.dateStrToDate(query.getStartTime()));
        Date endTime = DateUtilWen.getDayEndTime(DateUtilWen.dateStrToDate(query.getEndTime()));
        // 根据搜索关键字查询入库记录
        List<PageWarehouseRecordByKeyVo> warehouseRecordByKeyVoList = warehouseRecordMapper.listWarehouseRecordByKey(query, startTime, endTime);
        // 获取入库记录编号列表
        List<Long> warehouseRecordIdList = new ArrayList<>();
        warehouseRecordByKeyVoList.forEach(pageWarehouseRecordByKeyVo -> warehouseRecordIdList.add(pageWarehouseRecordByKeyVo.getId()));
        // 根据入库记录编号列表获取入库物资列表
        List<DropDown> listWarehouseMaterialByWarehouseRecordIdList = warehouseMaterialMapper.listWarehouseMaterialByWarehouseRecordIdList(warehouseRecordIdList);
        // 根据记录编号转为封装map
        Map<Long, List<DropDown>> mapWarehouseMaterialByWarehouseRecordId = commonFunc.mapDropDownById(listWarehouseMaterialByWarehouseRecordIdList);
        // 设置入库物资
        warehouseRecordByKeyVoList.forEach(pageWarehouseRecordByKeyVo -> {
            // 获取入库物资
            List<DropDown> materialList = mapWarehouseMaterialByWarehouseRecordId.get(pageWarehouseRecordByKeyVo.getId());
            // 判断该入库记录是否存在物资
            if (ObjUtil.isNotEmpty(materialList)) {
                // 获取入库物资名称列表
                List<String> materialNameList = new ArrayList<>();
                materialList.forEach(material -> materialNameList.add(material.getName()));
                pageWarehouseRecordByKeyVo.setMaterialName(commonUtil.joinStrBySymbol(materialNameList, Constant.COMMA));
            }
        });
        // 根据物资名称过滤出库记录
        if (ObjUtil.isNotEmpty(materialName)) {
            warehouseRecordByKeyVoList = warehouseRecordByKeyVoList.stream()
                    .filter(pageWarehouseRecordByKeyVo -> pageWarehouseRecordByKeyVo.getMaterialName().contains(materialName))
                    .collect(Collectors.toList());
        }
        // 封装返回数据
        PageResult<PageWarehouseRecordByKeyVo> pageResult = pageUtil.packPageResultData(warehouseRecordByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result listWarehouse() {
        return Result.success(BeanUtil.copyToList(warehouseFunc.listNotDelWarehouse(), DropDown.class));
    }

    @Override
    @Transactional
    public Result addWarehouseRecord(AddWarehouseRecordQuery query) {
        // 初始化入库记录
        WarehouseRecord warehouseRecord = BeanUtil.toBean(query, WarehouseRecord.class);
        warehouseRecord.setWarehouseFlag(AuditEnum.STAY_AUDIT.getStatus());
        warehouseRecord.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        warehouseRecordMapper.insert(warehouseRecord);
        // 根据物资编号分组入库物资列表
        List<AddWarehouseRecordQuery.AddWarehouseMaterialQuery> warehouseMaterialQueryList = query.getWarehouseMaterialQueryList();
        Map<Long, List<AddWarehouseRecordQuery.AddWarehouseMaterialQuery>> materialMap = warehouseMaterialQueryList.parallelStream()
                .collect(Collectors.groupingBy(AddWarehouseRecordQuery.AddWarehouseMaterialQuery::getMaterialId));
        // 初始化入库物资
        List<WarehouseMaterial> warehouseMaterialList = new ArrayList<>();
        for (Long materialId : materialMap.keySet()) {
            // 获取当前物资编号对应的入库列表
            List<AddWarehouseRecordQuery.AddWarehouseMaterialQuery> addWarehouseMaterialQuery = materialMap.get(materialId);
            // 入库物资数量
            Integer[] materialNum = {0};
            addWarehouseMaterialQuery.forEach(warehouseMaterialQuery ->
                    materialNum[0] += warehouseMaterialQuery.getWarehouseNum()
            );
            // 初始化入库物资
            WarehouseMaterial warehouseMaterial = BeanUtil.toBean(addWarehouseMaterialQuery.get(0), WarehouseMaterial.class);
            warehouseMaterial.setWarehouseRecordId(warehouseRecord.getId());
            warehouseMaterial.setWarehouseNum(materialNum[0]);
            warehouseMaterial.setInventory(materialNum[0]);
            // 添加到入库物资列表
            warehouseMaterialList.add(warehouseMaterial);
        }
        // 批量插入入库物资
        warehouseMaterialFunc.saveBatch(warehouseMaterialList);
        return Result.success();
    }

    @Override
    public Result getWarehouseRecordById(Long warehouseRecordId) {
        // 根据编号获取入库记录信息
        GetWarehouseRecordByIdVo getWarehouseRecordByIdVo = warehouseRecordMapper.getWarehouseRecordById(warehouseRecordId);
        // 判断是否为空
        AssertUtil.notNull(getWarehouseRecordByIdVo, ExceptionEnum.DATA_NOT_EXIST);
        // 根据入库记录编号获取入库物资列表
        List<GetWarehouseRecordByIdVo.GetWarehouseMaterialByIdVo> getWarehouseMaterialByIdVoList = warehouseMaterialMapper.listWarehouseMaterialByWarehouseRecordId(warehouseRecordId);
        // 入库物资列表编序号
        getWarehouseMaterialByIdVoList = pageUtil.rank(getWarehouseMaterialByIdVoList);
        // 设置入库记录物资列表
        getWarehouseRecordByIdVo.setWarehouseMaterialByIdVoList(getWarehouseMaterialByIdVoList);
        return Result.success(getWarehouseRecordByIdVo);
    }

    @Override
    public Result auditWarehouseRecord(AuditWarehouseRecordQuery query) {
        // 验证审核结果是否合法
        boolean checkAuditResult = !Objects.equals(query.getAuditResult(), AuditEnum.PASS.getStatus()) &&
                !Objects.equals(query.getAuditResult(), AuditEnum.NO_PASS.getStatus());
        AssertUtil.notTrue(checkAuditResult, ExceptionEnum.AUDIT_RESULT_ERROR);
        // 根据编号获取入库记录
        WarehouseRecord warehouseRecord = warehouseRecordMapper.selectById(query.getWarehouseRecordId());
        // 验证是否为空
        AssertUtil.notNull(warehouseRecord, ExceptionEnum.DATA_NOT_EXIST);
        // 验证入库记录是否已审核
        boolean checkAudit = !Objects.equals(warehouseRecord.getWarehouseFlag(), AuditEnum.STAY_AUDIT.getStatus());
        AssertUtil.notTrue(checkAudit, ExceptionEnum.AUDIT_REPEAT);
        // 封装修改
        warehouseRecord.setWarehouseFlag(
                Objects.equals(query.getAuditResult(), AuditEnum.PASS.getStatus()) ?
                        AuditEnum.HAVE_PASS.getStatus() :
                        AuditEnum.NOT_PASS.getStatus()
        );
        // 修改
        warehouseRecordMapper.updateById(warehouseRecord);
        return Result.success();
    }

    @Override
    public Result deleteWarehouseRecord(Long warehouseRecordId) {
        // 封装修改
        WarehouseRecord warehouseRecord = new WarehouseRecord();
        warehouseRecord.setId(warehouseRecordId);
        warehouseRecord.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        warehouseRecordMapper.updateById(warehouseRecord);
        return Result.success();
    }
}
