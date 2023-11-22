package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.ew.gerocomium.common.constant.*;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.CommonUtil;
import com.ew.gerocomium.common.util.DateUtilWen;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.*;
import com.ew.gerocomium.dao.po.*;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.dao.vo.*;
import com.ew.gerocomium.service.OutboundRecordService;
import com.ew.gerocomium.service.common.CommonFunc;
import com.ew.gerocomium.service.common.OutboundMaterialFunc;
import com.ew.gerocomium.service.common.StaffFunc;
import com.ew.gerocomium.service.common.WarehouseMaterialFunc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OutboundRecordServiceImpl implements OutboundRecordService {
    @Resource
    private OutboundRecordMapper outboundRecordMapper;
    @Resource
    private OutboundMaterialMapper outboundMaterialMapper;
    @Resource
    private OutboundMaterialFunc outboundMaterialFunc;
    @Resource
    private WarehouseMaterialMapper warehouseMaterialMapper;
    @Resource
    private WarehouseMaterialFunc warehouseMaterialFunc;
    @Resource
    private StaffMapper staffMapper;
    @Resource
    private StaffFunc staffFunc;
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private CommonFunc commonFunc;
    @Resource
    private CommonUtil commonUtil;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageOutboundRecordByKey(PageOutboundRecordByKeyQuery query) {
        String materialName = query.getMaterialName();
        String recipient = query.getRecipient();
        // 获取开始/结束时间
        Date startTime = DateUtilWen.getDayStartTime(DateUtilWen.dateStrToDate(query.getStartTime()));
        Date endTime = DateUtilWen.getDayEndTime(DateUtilWen.dateStrToDate(query.getEndTime()));
        // 根据搜索关键字查询出库记录
        List<PageOutboundRecordByKeyVo> outboundRecordByKeyVoList = outboundRecordMapper.listOutboundRecordByKey(query, startTime, endTime);
        // 获取指向集合或流开头的迭代器
        Iterator<PageOutboundRecordByKeyVo> outboundRecordByKeyVoIterator = outboundRecordByKeyVoList.stream().iterator();
        // 封装员工/老人编号列表
        List<Long> staffIdList = new ArrayList<>();
        List<Long> elderIdList = new ArrayList<>();
        while (outboundRecordByKeyVoIterator.hasNext()) {
            // 获取列表元素
            PageOutboundRecordByKeyVo pageOutboundRecordByKeyVo = outboundRecordByKeyVoIterator.next();
            // 领用人类型为员工
            if (Objects.equals(pageOutboundRecordByKeyVo.getRecipientType(), RecipientEnum.STAFF.getType())) {
                staffIdList.add(pageOutboundRecordByKeyVo.getRecipientId());
                // 领用人类型为老人
            } else if (Objects.equals(pageOutboundRecordByKeyVo.getRecipientType(), RecipientEnum.ELDER.getType())) {
                elderIdList.add(pageOutboundRecordByKeyVo.getRecipientId());
            }
        }
        // 员工/老人编号去重
        staffIdList = staffIdList.stream().distinct().collect(Collectors.toList());
        elderIdList = elderIdList.stream().distinct().collect(Collectors.toList());
        // 根据员工编号列表获取员工
        List<Staff> staffList = ObjUtil.isNotEmpty(staffIdList) ? staffMapper.selectBatchIds(staffIdList) : new ArrayList<>();
        // 根据老人编号列表获取老人
        List<Elder> elderList = ObjUtil.isNotEmpty(elderIdList) ? elderMapper.selectBatchIds(elderIdList) : new ArrayList<>();
        // 设置领用人
        outboundRecordByKeyVoList.forEach(pageOutboundRecordByKeyVo -> {
            // 领用人类型为员工
            if (Objects.equals(pageOutboundRecordByKeyVo.getRecipientType(), RecipientEnum.STAFF.getType())) {
                staffList.forEach(staff -> {
                    if (Objects.equals(pageOutboundRecordByKeyVo.getRecipientId(), staff.getId())) {
                        pageOutboundRecordByKeyVo.setRecipient(staff.getName());
                    }
                });
                // 领用人类型为老人
            } else if (Objects.equals(pageOutboundRecordByKeyVo.getRecipientType(), RecipientEnum.ELDER.getType())) {
                elderList.forEach(elder -> {
                    if (Objects.equals(pageOutboundRecordByKeyVo.getRecipientId(), elder.getId())) {
                        pageOutboundRecordByKeyVo.setRecipient(elder.getName());
                    }
                });
            }
        });
        // 根据领用人过滤出库记录
        if (ObjUtil.isNotEmpty(recipient)) {
            outboundRecordByKeyVoList = outboundRecordByKeyVoList.stream()
                    .filter(pageOutboundRecordByKeyVo -> pageOutboundRecordByKeyVo.getRecipient().contains(recipient))
                    .collect(Collectors.toList());
        }
        // 获取出库记录编号列表
        List<Long> outboundRecordIdList = new ArrayList<>();
        outboundRecordByKeyVoList.forEach(pageOutboundRecordByKeyVo -> outboundRecordIdList.add(pageOutboundRecordByKeyVo.getId()));
        // 根据出库记录编号列表获取出库物资列表
        List<DropDown> listOutboundMaterialByOutboundRecordIdList = outboundMaterialMapper.listOutboundMaterialByOutboundRecordIdList(outboundRecordIdList);
        // 根据记录编号转为封装map
        Map<Long, List<DropDown>> mapOutboundMaterialByOutboundRecordId = commonFunc.mapDropDownById(listOutboundMaterialByOutboundRecordIdList);
        // 设置出库物资
        outboundRecordByKeyVoList.forEach(pageOutboundRecordByKeyVo -> {
            // 获取出库物资
            List<DropDown> materialList = mapOutboundMaterialByOutboundRecordId.get(pageOutboundRecordByKeyVo.getId());
            // 判断该出库记录是否存在物资
            if (ObjUtil.isNotEmpty(materialList)) {
                // 获取出库物资名称列表
                List<String> materialNameList = new ArrayList<>();
                materialList.forEach(material -> materialNameList.add(material.getName()));
                pageOutboundRecordByKeyVo.setMaterialName(commonUtil.joinStrBySymbol(materialNameList, Constant.COMMA));
            }
        });
        // 根据物资名称过滤出库记录
        if (ObjUtil.isNotEmpty(materialName)) {
            outboundRecordByKeyVoList = outboundRecordByKeyVoList.stream()
                    .filter(pageOutboundRecordByKeyVo -> pageOutboundRecordByKeyVo.getMaterialName().contains(materialName))
                    .collect(Collectors.toList());
        }
        // 封装返回数据
        PageResult<PageOutboundRecordByKeyVo> pageResult = pageUtil.packPageResultData(outboundRecordByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result pageSearchStaffByKey(PageSearchStaffByKeyQuery query) {
        return commonFunc.pageSearchStaffByKeyResult(query, null);
    }

    @Override
    public Result pageWarehouseMaterialByKey(PageWarehouseMaterialByKeyQuery query) {
        // 根据关键字获取仓库物资列表
        List<PageWarehouseMaterialByKeyVo> pageWarehouseMaterialByKeyVoList = warehouseMaterialMapper.listWarehouseMaterialByKey(query);
        // 封装返回数据
        PageResult<PageWarehouseMaterialByKeyVo> pageResult = pageUtil.packPageResultData(pageWarehouseMaterialByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    @Transactional
    public Result addOutboundRecord(AddOutboundRecordQuery query) {
        // 初始化出库记录
        OutboundRecord outboundRecord = BeanUtil.toBean(query, OutboundRecord.class);
        outboundRecord.setOutboundFlag(AuditEnum.STAY_AUDIT.getStatus());
        outboundRecord.setDelFlag(YesNoEnum.NO.getCode());
        // 新增出库记录
        outboundRecordMapper.insert(outboundRecord);
        // 将出库物资列表根据入库物资编号分组
        Map<Long, List<AddOutboundRecordQuery.AddOutboundMaterialQuery>> materialMap = query.getOutboundMaterialQueryList()
                .parallelStream()
                .collect(Collectors.groupingBy(AddOutboundRecordQuery.AddOutboundMaterialQuery::getWarehouseMaterialId));
        // 获取入库物资编号
        List<Long> warehouseMaterialIdList = new ArrayList<>(materialMap.keySet());
        // 根据入库物资编号列表获取入库物资列表
        List<WarehouseMaterial> warehouseMaterialList = warehouseMaterialMapper.selectBatchIds(warehouseMaterialIdList);
        // 实例化出库物资列表
        List<OutboundMaterial> outboundMaterialList = new ArrayList<>();
        // 实例化修改入库物资库存量列表
        List<WarehouseMaterial> updateWarehouseMaterialList = new ArrayList<>();
        for (Long warehouseMaterialId : materialMap.keySet()) {
            // 获取当前出库物资列表
            List<AddOutboundRecordQuery.AddOutboundMaterialQuery> addOutboundMaterialQueryList = materialMap.get(warehouseMaterialId);
            // 出库物资数量
            Integer[] materialNum = {0};
            addOutboundMaterialQueryList.forEach(outboundMaterialQuery ->
                    materialNum[0] += outboundMaterialQuery.getOutboundNum()
            );
            // 过滤当前入库物资
            List<WarehouseMaterial> warehouseMaterials = warehouseMaterialList.stream()
                    .filter(warehouseMaterial ->
                            Objects.equals(warehouseMaterial.getId(), warehouseMaterialId)
                    )
                    .collect(Collectors.toList());
            WarehouseMaterial warehouseMaterial = warehouseMaterials.get(0);
            // 验证出库物资数量是否超过入库物资库存量
            boolean checkInventory = materialNum[0] > warehouseMaterial.getInventory();
            AssertUtil.notTrue(checkInventory, ExceptionEnum.OUTBOUND_NUM_ERROR);
            // 封装出库物资
            OutboundMaterial outboundMaterial = new OutboundMaterial();
            outboundMaterial.setOutboundRecordId(outboundRecord.getId());
            outboundMaterial.setWarehouseMaterialId(warehouseMaterial.getId());
            outboundMaterial.setMaterialId(warehouseMaterial.getMaterialId());
            outboundMaterial.setOutboundNum(materialNum[0]);
            // 添加出库物资到列表
            outboundMaterialList.add(outboundMaterial);
            // 封装修改入库物资
            warehouseMaterial.setInventory(warehouseMaterial.getInventory() - materialNum[0]);
            updateWarehouseMaterialList.add(warehouseMaterial);
        }
        // 批量插入出库物资
        outboundMaterialFunc.saveBatch(outboundMaterialList);
        // 批量修改入库物资库存量
        warehouseMaterialFunc.updateBatchById(updateWarehouseMaterialList);
        return Result.success();
    }

    @Override
    public Result getOutboundRecordById(Long outboundRecordId) {
        // 根据编号获取出库记录信息
        GetOutboundRecordByIdVo getOutboundRecordByIdVo = outboundRecordMapper.getOutboundRecordById(outboundRecordId);
        // 判断是否为空
        AssertUtil.notNull(getOutboundRecordByIdVo, ExceptionEnum.DATA_NOT_EXIST);
        // 领用人类型为老人
        if (Objects.equals(getOutboundRecordByIdVo.getRecipientType(), RecipientEnum.ELDER.getType())) {
            // 根据领用人编号获取老人
            Elder elder = elderMapper.selectById(getOutboundRecordByIdVo.getRecipientId());
            // 验证老人是否存在
            if (ObjUtil.isNotEmpty(elder)) {
                // 设置领用人
                getOutboundRecordByIdVo.setRecipient(elder.getName());
            }
            // 领用人类型为员工
        } else if (Objects.equals(getOutboundRecordByIdVo.getRecipientType(), RecipientEnum.STAFF.getType())) {
            // 根据领用人编号获取员工
            Staff staff = staffMapper.selectById(getOutboundRecordByIdVo.getRecipientId());
            // 验证员工是否存在
            if (ObjUtil.isNotEmpty(staff)) {
                // 设置领用人
                getOutboundRecordByIdVo.setRecipient(staff.getName());
            }
        }
        // 根据出库记录编号获取出库物资列表
        List<GetOutboundRecordByIdVo.GetOutboundMaterialByIdVo> listOutboundMaterialByWarehouseRecordId = outboundMaterialMapper.listOutboundMaterialByWarehouseRecordId(outboundRecordId);
        // 出库物资列表编序号
        listOutboundMaterialByWarehouseRecordId = pageUtil.rank(listOutboundMaterialByWarehouseRecordId);
        // 设置出库记录物资列表
        getOutboundRecordByIdVo.setOutboundMaterialByIdVoList(listOutboundMaterialByWarehouseRecordId);
        return Result.success(getOutboundRecordByIdVo);
    }

    @Override
    @Transactional
    public Result auditOutboundRecord(AuditOutboundRecordQuery query) {
        // 验证审核结果是否合法
        boolean checkAuditResult = !Objects.equals(query.getAuditResult(), AuditEnum.PASS.getStatus()) &&
                !Objects.equals(query.getAuditResult(), AuditEnum.NO_PASS.getStatus());
        AssertUtil.notTrue(checkAuditResult, ExceptionEnum.AUDIT_RESULT_ERROR);
        // 根据编号获取出库记录
        OutboundRecord outboundRecord = outboundRecordMapper.selectById(query.getOutboundRecordId());
        // 验证是否为空
        AssertUtil.notNull(outboundRecord, ExceptionEnum.DATA_NOT_EXIST);
        // 验证出库记录是否已审核
        boolean checkAudit = !Objects.equals(outboundRecord.getOutboundFlag(), AuditEnum.STAY_AUDIT.getStatus());
        AssertUtil.notTrue(checkAudit, ExceptionEnum.AUDIT_REPEAT);
        // 通过标记
        boolean passFlag = Objects.equals(query.getAuditResult(), AuditEnum.PASS.getStatus());
        // 封装修改出库记录
        outboundRecord.setOutboundFlag(
                passFlag ?
                        AuditEnum.HAVE_PASS.getStatus() :
                        AuditEnum.NOT_PASS.getStatus()
        );
        // 修改出库记录
        outboundRecordMapper.updateById(outboundRecord);
        // 若审核结果为不通过
        if (!passFlag) {
            // 回退该出库记录所有物资
            outboundMaterialFunc.materialRollback(query.getOutboundRecordId());
        }
        return Result.success();
    }

    @Override
    public Result deleteOutboundRecord(Long outboundRecordId) {
        // 根据编号获取出库记录
        OutboundRecord outboundRecord = outboundRecordMapper.selectById(outboundRecordId);
        // 验证是否为空
        AssertUtil.notNull(outboundRecord, ExceptionEnum.DATA_NOT_EXIST);
        // 验证是否已被删除
        AssertUtil.notTrue(Objects.equals(outboundRecord.getDelFlag(), YesNoEnum.YES.getCode()), ExceptionEnum.DEL_REPEAT);
        // 封装修改出库记录
        outboundRecord.setDelFlag(YesNoEnum.YES.getCode());
        // 修改出库记录
        outboundRecordMapper.updateById(outboundRecord);
        // 若出库状态为待审核
        if (Objects.equals(outboundRecord.getOutboundFlag(), AuditEnum.STAY_AUDIT.getStatus())) {
            // 回退该出库记录所有物资
            outboundMaterialFunc.materialRollback(outboundRecordId);
        }
        return Result.success();
    }
}
