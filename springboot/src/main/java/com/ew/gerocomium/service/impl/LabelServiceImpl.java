package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.LabelMapper;
import com.ew.gerocomium.dao.mapper.LabelTypeMapper;
import com.ew.gerocomium.dao.po.Label;
import com.ew.gerocomium.dao.po.LabelType;
import com.ew.gerocomium.dao.query.OperateLabelQuery;
import com.ew.gerocomium.dao.query.OperateLabelTypeQuery;
import com.ew.gerocomium.dao.vo.ListLabelVo;
import com.ew.gerocomium.dao.vo.OperateLabelTypeVo;
import com.ew.gerocomium.dao.vo.OperateLabelVo;
import com.ew.gerocomium.service.LabelService;
import com.ew.gerocomium.service.common.LabelFunc;
import com.ew.gerocomium.service.common.LabelTypeFunc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LabelServiceImpl implements LabelService {
    @Resource
    private LabelTypeMapper labelTypeMapper;
    @Resource
    private LabelTypeFunc labelTypeFunc;
    @Resource
    private LabelMapper labelMapper;
    @Resource
    private LabelFunc labelFunc;

    @Override
    public Result listLabel() {
        // 获取未被删除的标签分类
        List<LabelType> listNotDelLabelType = labelTypeFunc.listNotDelLabelType();
        // 获取未被删除的标签
        List<Label> listNotDelLabel = labelFunc.listNotDelLabel();
        // 根据标签分类编号对标签进行分组
        Map<Long, List<Label>> listMap = labelFunc.mapNotDelLabel(listNotDelLabel);
        // 封装返回数据
        List<ListLabelVo> listLabelVoList = new ArrayList<>();
        for (LabelType labelType : listNotDelLabelType) {
            // 实体转换
            ListLabelVo listLabelVo = BeanUtil.toBean(labelType, ListLabelVo.class);
            // 获取该分类标签
            List<Label> labelList = listMap.get(labelType.getId());
            if(ObjUtil.isNotEmpty(labelList)){
                // 列表实体转换
                List<ListLabelVo.LabelItem> labelItemList = BeanUtil.copyToList(labelList, ListLabelVo.LabelItem.class);
                // 设置标签的分类编号
                labelItemList = labelItemList.stream()
                        .peek(labelItem -> labelItem.setTypeId(labelType.getId()))
                        .collect(Collectors.toList());
                listLabelVo.setLabelItemList(labelItemList);
            }
            listLabelVoList.add(listLabelVo);
        }
        return Result.success(listLabelVoList);
    }

    @Override
    public Result addLabelType(OperateLabelTypeQuery query) {
        // 判断分类是否超过总数限制
        AssertUtil.notTrue(labelTypeFunc.checkTypeTotal(), ExceptionEnum.LABEL_TYPE_OUT);
        // 判断分类名称是否重复
        AssertUtil.isNull(labelTypeFunc.getLabelTypeByName(query.getName()), ExceptionEnum.LABEL_TYPE_REPEAT);
        // 初始化标签分类
        query.setId(null);
        LabelType labelType = labelTypeFunc.initLabelType(query);
        // 新增
        labelTypeMapper.insert(labelType);
        return Result.success();
    }

    @Override
    public Result getLabelTypeById(Long typeId) {
        // 根据编号获取标签分类
        LabelType labelType = labelTypeMapper.selectById(typeId);
        // 判断是否为空
        AssertUtil.notNull(labelType, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(labelType, OperateLabelTypeVo.class));
    }

    @Override
    public Result editLabelType(OperateLabelTypeQuery query) {
        // 判断分类名称是否重复
        LabelType labelTypeByName = labelTypeFunc.getLabelTypeByName(query.getName());
        boolean checkTypeName = labelTypeByName != null && !Objects.equals(labelTypeByName.getId(), query.getId());
        AssertUtil.notTrue(checkTypeName, ExceptionEnum.LABEL_TYPE_REPEAT);
        // 修改
        labelTypeMapper.updateById(BeanUtil.toBean(query, LabelType.class));
        return Result.success();
    }

    @Override
    public Result deleteLabelType(Long typeId) {
        // 判断是否存在子标签
        AssertUtil.notTrue(labelFunc.checkLabelItem(typeId),ExceptionEnum.LABEL_NOT_NULL);
        // 封装修改
        LabelType labelType = new LabelType();
        labelType.setId(typeId);
        labelType.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        labelTypeMapper.updateById(labelType);
        return Result.success();
    }

    @Override
    public Result addLabel(OperateLabelQuery query) {
        // 判断标签在该分类中是否超过总数限制
        AssertUtil.notTrue(labelFunc.checkLabelTotal(query.getTypeId()), ExceptionEnum.LABEL_OUT);
        // 判断标签在该分类中是否重复
        AssertUtil.isNull(labelFunc.getLabelByName(query), ExceptionEnum.LABEL_REPEAT);
        // 初始化标签
        query.setId(null);
        Label label = labelFunc.initLabel(query);
        // 新增
        labelMapper.insert(label);
        return Result.success();
    }

    @Override
    public Result getLabelById(Long labelId) {
        // 根据编号获取标签
        Label label = labelMapper.selectById(labelId);
        // 判断是否为空
        AssertUtil.notNull(label, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(label, OperateLabelVo.class));
    }

    @Override
    public Result editLabel(OperateLabelQuery query) {
        // 判断标签在该分类中是否重复
        Label labelByName = labelFunc.getLabelByName(query);
        boolean checkLabelName = labelByName != null && !Objects.equals(labelByName.getId(), query.getId());
        AssertUtil.notTrue(checkLabelName, ExceptionEnum.LABEL_REPEAT);
        // 修改
        labelMapper.updateById(BeanUtil.toBean(query, Label.class));
        return Result.success();
    }

    @Override
    public Result deleteLabel(Long labelId) {
        // 封装修改
        Label label = new Label();
        label.setId(labelId);
        label.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        labelMapper.updateById(label);
        return Result.success();
    }
}
