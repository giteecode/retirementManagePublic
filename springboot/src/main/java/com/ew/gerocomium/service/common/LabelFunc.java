package com.ew.gerocomium.service.common;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.mapper.LabelMapper;
import com.ew.gerocomium.dao.po.Label;
import com.ew.gerocomium.dao.query.OperateLabelQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 标签表公共方法
 */
@Component
public class LabelFunc {
    @Resource
    private LabelMapper labelMapper;

    /**
     * 获取未被删除的标签
     *
     * @return
     */
    public List<Label> listNotDelLabel() {
        return labelMapper.selectList(new LambdaQueryWrapper<Label>()
                .eq(Label::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 根据标签分类编号对标签进行分组
     *
     * @return
     */
    public Map<Long, List<Label>> mapNotDelLabel(List<Label> labelList) {
        return labelList.parallelStream().collect(Collectors.groupingBy(Label::getTypeId));
    }

    /**
     * 判断标签分类下是否存在子标签
     *
     * @return
     */
    public Boolean checkLabelItem(Long typeId) {
        Long labelTotal = labelMapper.selectCount(new LambdaQueryWrapper<Label>()
                .eq(Label::getTypeId, typeId)
                .eq(Label::getDelFlag, YesNoEnum.NO.getCode()));
        return labelTotal > 0;
    }

    /**
     * 判断标签在该分类中是否超过总数限制
     *
     * @return
     */
    public Boolean checkLabelTotal(Long typeId) {
        Long labelTotal = labelMapper.selectCount(new LambdaQueryWrapper<Label>()
                .eq(Label::getTypeId, typeId)
                .eq(Label::getDelFlag, YesNoEnum.NO.getCode()));
        return labelTotal >= Constant.TOTAL_LIMIT;
    }

    /**
     * 根据编号过滤标签信息
     *
     * @param labelList
     * @param labelId
     * @return
     */
    public Label filterLabelById(List<Label> labelList, Long labelId) {
        List<Label> collect = labelList.stream()
                .filter(label -> Objects.equals(label.getId(), labelId))
                .collect(Collectors.toList());
        return collect.size() == 0 ? null : collect.get(0);
    }

    /**
     * 根据标签名称获取信息
     *
     * @return
     */
    public Label getLabelByName(OperateLabelQuery operateLabelQuery) {
        return labelMapper.selectOne(new LambdaQueryWrapper<Label>()
                .eq(Label::getTypeId, operateLabelQuery.getTypeId())
                .eq(Label::getName, operateLabelQuery.getName())
                .eq(Label::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 初始化标签
     *
     * @return
     */
    public Label initLabel(OperateLabelQuery operateLabelQuery) {
        Label label = BeanUtil.toBean(operateLabelQuery, Label.class);
        label.setDelFlag(YesNoEnum.NO.getCode());
        return label;
    }
}
