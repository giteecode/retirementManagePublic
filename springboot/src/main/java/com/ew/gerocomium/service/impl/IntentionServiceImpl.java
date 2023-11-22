package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.ew.gerocomium.common.constant.CheckEnum;
import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.CommonUtil;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.*;
import com.ew.gerocomium.dao.po.*;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.dao.vo.*;
import com.ew.gerocomium.service.IntentionService;
import com.ew.gerocomium.service.common.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IntentionServiceImpl implements IntentionService {
    @Resource
    private CommonFunc commonFunc;
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private LabelMapper labelMapper;
    @Resource
    private LabelTypeFunc labelTypeFunc;
    @Resource
    private LabelFunc labelFunc;
    @Resource
    private ElderFunc elderFunc;
    @Resource
    private ElderLabelFunc elderLabelFunc;
    @Resource
    private PageUtil pageUtil;
    @Resource
    private VisitPlanMapper visitPlanMapper;
    @Resource
    private VisitPlanFunc visitPlanFunc;
    @Resource
    private CommunicationRecordMapper communicationRecordMapper;
    @Resource
    private CommunicationRecordFunc communicationRecordFunc;
    @Resource
    private CommonUtil commonUtil;

    @Override
    public Result listLabel() {
        return Result.success(BeanUtil.copyToList(labelFunc.listNotDelLabel(), DropDown.class));
    }

    @Override
    public Result pageIntentionByKey(PageIntentionByKeyQuery query) {
        Long labelId = query.getLabelId();
        // 根据姓名和联系电话获取意向跟进老人列表
        List<Elder> listElderByKey = elderFunc.listElderByKey(query.getElderName(), query.getElderPhone(), CheckEnum.INTENTION.getStatus());
        // 获取老人列表编号
        List<Long> elderIdList = new ArrayList<>();
        listElderByKey.forEach(elder -> elderIdList.add(elder.getId()));
        // 根据老人编号列表查询老人标签
        Map<Long, List<ElderLabel>> elderLabelMap = elderLabelFunc.mapElderLabel(elderIdList);
        // 获取所有未被删除的客户标签
        List<Label> listNotDelLabel = labelFunc.listNotDelLabel();
        // 获取迭代器
        Iterator<Elder> elderIterator = listElderByKey.stream().iterator();
        // 实例化返回数据
        List<PageIntentionByKeyVo> pageIntentionByKeyVoList = new ArrayList<>();
        while (elderIterator.hasNext()) {
            // 获取列表元素
            Elder elder = elderIterator.next();
            // 实体转换
            PageIntentionByKeyVo pageIntentionByKeyVo = BeanUtil.toBean(elder, PageIntentionByKeyVo.class);
            // 获取老人标签
            List<ElderLabel> elderLabelList = elderLabelMap.get(elder.getId());
            // 老人存在标签
            if (ObjUtil.isNotEmpty(elderLabelList)) {
                // 实例化老人标签列表
                List<PageIntentionByKeyVo.LabelVo> labelVoList = new ArrayList<>();
                // 实例化老人标签编号列表
                List<String> elderLabelIdList = new ArrayList<>();
                // 遍历老人标签
                elderLabelList.forEach(elderLabel -> {
                            // 根据编号过滤标签信息
                            Label label = labelFunc.filterLabelById(listNotDelLabel, elderLabel.getLabelId());
                            if (ObjUtil.isNotEmpty(label)) {
                                // 实体转换并添加到列表
                                labelVoList.add(BeanUtil.toBean(label, PageIntentionByKeyVo.LabelVo.class));
                                // 将老人标签编号添加到列表
                                elderLabelIdList.add(label.getId().toString());
                            }
                        }
                );
                // 设值
                pageIntentionByKeyVo.setLabelIdStr(commonUtil.joinStrBySymbol(elderLabelIdList, Constant.COMMA));
                pageIntentionByKeyVo.setLabelVoList(labelVoList);
            }
            // 添加列表
            pageIntentionByKeyVoList.add(pageIntentionByKeyVo);
        }
        // 根据标签编号过滤意向客户
        if (ObjUtil.isNotEmpty(labelId)) {
            pageIntentionByKeyVoList = pageIntentionByKeyVoList.stream()
                    .filter(pageIntentionByKeyVo -> pageIntentionByKeyVo.getLabelIdStr().contains(labelId.toString()))
                    .collect(Collectors.toList());
        }
        // 封装返回数据
        PageResult<PageIntentionByKeyVo> pageResult = pageUtil.packPageResultData(pageIntentionByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result pageSearchElderByKey(PageSearchElderByKeyQuery query) {
        List<String> checkFlagList = new ArrayList<>(Arrays.asList(CheckEnum.CONSULT.getStatus(), CheckEnum.EXIT.getStatus()));
        // 根据姓名和联系电话获取咨询和退住老人列表
        return commonFunc.pageSearchElderByKeyResult(query, checkFlagList);
    }

    @Override
    public Result addIntention(OperateIntentionQuery query) {
        // 根据身份证号获取老人信息
        Elder elderByIdNum = elderFunc.getElderByIdNum(query.getIdNum());
        if (ObjUtil.isEmpty(elderByIdNum)) {
            // 初始化老人
            query.setId(null);
            Elder elder = BeanUtil.toBean(query, Elder.class);
            elder.setBalance(BigDecimal.valueOf(0));
            elder.setCheckFlag(CheckEnum.INTENTION.getStatus());
            elderMapper.insert(elder);
        } else {
            // 判断该老人状态
            elderFunc.checkIntention(elderByIdNum);
            // 封装修改
            Elder elder = BeanUtil.toBean(query, Elder.class);
            elder.setId(elderByIdNum.getId());
            // 修改
            elderMapper.updateById(elder);
        }
        return Result.success();
    }

    @Override
    public Result getIntentById(Long elderId) {
        // 根据编号获取意向客户
        Elder elder = elderMapper.selectById(elderId);
        // 判断是否为空
        AssertUtil.notNull(elder, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(elder, OperateIntentionVo.class));
    }

    @Override
    public Result getElderLabelById(Long elderId) {
        return Result.success(labelMapper.listElderLabelById(elderId));
    }

    @Override
    public Result editIntention(OperateIntentionQuery query) {
        // 验证老人
        elderFunc.checkElder(query.getId(), query.getIdNum());
        // 封装修改
        Elder elder = BeanUtil.toBean(query, Elder.class);
        // 修改
        elderMapper.updateById(elder);
        return Result.success();
    }

    @Override
    public Result getEditElderLabelById(Long elderId) {
        // 获取老人标签
        List<GetElderLabelByIdLabelVo> elderLabelList = labelMapper.listElderLabelById(elderId);
        // 获取未被删除的标签分类
        List<LabelType> listNotDelLabelType = labelTypeFunc.listNotDelLabelType();
        // 获取未被删除的标签
        List<Label> listNotDelLabel = labelFunc.listNotDelLabel();
        // 根据标签分类编号对标签进行分组
        Map<Long, List<Label>> listMap = labelFunc.mapNotDelLabel(listNotDelLabel);
        // 封装返回数据
        List<GetEditElderLabelByIdVo> list = new ArrayList<>();
        listNotDelLabelType.forEach(labelType -> {
            // 实体转换
            GetEditElderLabelByIdVo labelTypeVo = BeanUtil.toBean(labelType, GetEditElderLabelByIdVo.class);
            // 获取该分类的标签列表
            List<Label> labelList = listMap.get(labelType.getId());
            // 该分类下有标签
            if (ObjUtil.isNotEmpty(labelList)) {
                List<GetEditElderLabelByIdVo.LabelTypeItem> labelTypeItemList = new ArrayList<>();
                labelList.forEach(label -> {
                    // 实体转换
                    GetEditElderLabelByIdVo.LabelTypeItem labelTypeItem = BeanUtil.toBean(label, GetEditElderLabelByIdVo.LabelTypeItem.class);
                    // 判断老人标签列表中是否存在该标签
                    List<GetElderLabelByIdLabelVo> collect = elderLabelList.stream()
                            .filter(ellderLabel -> Objects.equals(ellderLabel.getId(), label.getId()))
                            .collect(Collectors.toList());
                    // 设置老人是否拥有该标签
                    labelTypeItem.setIsCheck(ObjUtil.isNotEmpty(collect));
                    labelTypeItemList.add(labelTypeItem);
                });
                labelTypeVo.setLabelTypeItemList(labelTypeItemList);
                list.add(labelTypeVo);
            }
        });
        return Result.success(list);
    }

    @Override
    public Result editElderLabel(EditElderLabelQuery query) {
        elderLabelFunc.saveBatchElderLabel(query.getLabelIdList(), query.getElderId());
        return Result.success();
    }

    @Override
    public Result pageVisitPlan(PageVisitPlanQuery query) {
        Boolean completeFlag = query.getCompleteFlag();
        // 根据条件获取所有未被删除的回访计划
        List<VisitPlan> visitPlanList = visitPlanFunc.listNotDelVisitPlan(query.getElderId(), completeFlag);
        // 封装返回数据
        if (completeFlag) {
            // 实体转换
            List<PageYesVisitPlanVo> pageYesVisitPlanVoList = BeanUtil.copyToList(visitPlanList, PageYesVisitPlanVo.class);
            // 封装返回数据
            PageResult<PageYesVisitPlanVo> pageYesVisitPlanVoPageResult = pageUtil.packPageResultData(pageYesVisitPlanVoList, query.getPageNum(), query.getPageSize());
            return Result.success(pageYesVisitPlanVoPageResult);
        }
        // 实体转换
        List<PageNotVisitPlanVo> pageNotVisitPlanVoList = BeanUtil.copyToList(visitPlanList, PageNotVisitPlanVo.class);
        // 封装返回数据
        PageResult<PageNotVisitPlanVo> pageNotVisitPlanVoPageResult = pageUtil.packPageResultData(pageNotVisitPlanVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageNotVisitPlanVoPageResult);
    }

    @Override
    public Result addVisitPlan(AddVisitPlanQuery query) {
        // 根据标题获取回访计划
        AssertUtil.isNull(visitPlanFunc.getVisitPlanByTitle(query.getElderId(), query.getTitle()), ExceptionEnum.VISIT_PLAN_REPEAT);
        // 初始化回访计划
        VisitPlan visitPlan = BeanUtil.toBean(query, VisitPlan.class);
        visitPlan.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        visitPlanMapper.insert(visitPlan);
        return Result.success();
    }

    @Override
    public Result executeVisitPlan(CompleteVisitPlanQuery query) {
        // 封装修改
        VisitPlan visitPlan = BeanUtil.toBean(query, VisitPlan.class);
        // 修改
        visitPlanMapper.updateById(visitPlan);
        return Result.success();
    }

    @Override
    public Result deleteVisitPlan(Long visitPlanId) {
        // 封装修改
        VisitPlan visitPlan = new VisitPlan();
        visitPlan.setId(visitPlanId);
        visitPlan.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        visitPlanMapper.updateById(visitPlan);
        return Result.success();
    }

    @Override
    public Result pageCommunicationRecord(PageCommunicationRecordQuery query) {
        // 获取所有未被删除的沟通记录
        List<CommunicationRecord> visitPlanList = communicationRecordFunc.listNotDelCommunicationRecord(query.getElderId());
        // 实体转换
        List<PageCommunicationRecordVo> pageCommunicationRecordVoList = BeanUtil.copyToList(visitPlanList, PageCommunicationRecordVo.class);
        // 封装返回数据
        PageResult<PageCommunicationRecordVo> pageNotVisitPlanVoPageResult = pageUtil.packPageResultData(pageCommunicationRecordVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageNotVisitPlanVoPageResult);
    }

    @Override
    public Result addCommunicationRecord(AddCommunicationRecordQuery query) {
        // 初始化沟通记录
        CommunicationRecord communicationRecord = BeanUtil.toBean(query, CommunicationRecord.class);
        communicationRecord.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        communicationRecordMapper.insert(communicationRecord);
        return Result.success();
    }

    @Override
    public Result editCommunicationRecord(EditCommunicationRecordQuery query) {
        // 封装修改
        CommunicationRecord communicationRecord = BeanUtil.toBean(query, CommunicationRecord.class);
        // 修改
        communicationRecordMapper.updateById(communicationRecord);
        return Result.success();
    }

    @Override
    public Result deleteCommunicationRecord(Long communicationRecordId) {
        // 封装修改
        CommunicationRecord communicationRecord = new CommunicationRecord();
        communicationRecord.setId(communicationRecordId);
        communicationRecord.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        communicationRecordMapper.updateById(communicationRecord);
        return Result.success();
    }
}
