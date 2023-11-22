package com.ew.gerocomium.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.mapper.ActiveMapper;
import com.ew.gerocomium.dao.po.Active;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 活动表公共方法
 */
@Component
public class ActiveFunc {
    @Resource
    private ActiveMapper activeMapper;

    /**
     * 根据活动名称获取活动
     *
     * @param activeName
     * @return
     */
    public Active getActiveByName(String activeName) {
        return activeMapper.selectOne(new LambdaQueryWrapper<Active>()
                .eq(Active::getName, activeName)
                .eq(Active::getDelFlag, YesNoEnum.NO.getCode()));
    }
}
