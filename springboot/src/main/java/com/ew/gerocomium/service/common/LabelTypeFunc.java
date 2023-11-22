package com.ew.gerocomium.service.common;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.mapper.LabelTypeMapper;
import com.ew.gerocomium.dao.po.LabelType;
import com.ew.gerocomium.dao.query.OperateLabelTypeQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签分类表公共方法
 */
@Component
public class LabelTypeFunc {
    @Resource
    private LabelTypeMapper labelTypeMapper;

    /**
     * 获取未被删除的标签分类
     *
     * @return
     */
    public List<LabelType> listNotDelLabelType() {
        return labelTypeMapper.selectList(new LambdaQueryWrapper<LabelType>()
                .eq(LabelType::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 判断分类是否超过总数限制
     *
     * @return
     */
    public Boolean checkTypeTotal() {
        Long typeTotal = labelTypeMapper.selectCount(new LambdaQueryWrapper<LabelType>()
                .eq(LabelType::getDelFlag, YesNoEnum.NO.getCode()));
        return typeTotal >= Constant.TOTAL_LIMIT;
    }

    /**
     * 根据标签分类名称获取信息
     *
     * @return
     */
    public LabelType getLabelTypeByName(String typeName) {
        return labelTypeMapper.selectOne(new LambdaQueryWrapper<LabelType>()
                .eq(LabelType::getName, typeName)
                .eq(LabelType::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 初始化标签分类
     *
     * @return
     */
    public LabelType initLabelType(OperateLabelTypeQuery operateLabelTypeQuery) {
        LabelType labelType = BeanUtil.toBean(operateLabelTypeQuery, LabelType.class);
        labelType.setDelFlag(YesNoEnum.NO.getCode());
        return labelType;
    }
}
