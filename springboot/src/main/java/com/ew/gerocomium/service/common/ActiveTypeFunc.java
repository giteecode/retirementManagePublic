package com.ew.gerocomium.service.common;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.mapper.ActiveTypeMapper;
import com.ew.gerocomium.dao.po.ActiveType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 活动分类表公共方法
 */
@Component
public class ActiveTypeFunc {
    @Resource
    private ActiveTypeMapper activeTypeMapper;

    /**
     * 获取所有未被删除的活动分类
     *
     * @return
     */
    public List<ActiveType> listNotDelActiveType(String searchKey) {
        return activeTypeMapper.selectList(new LambdaQueryWrapper<ActiveType>()
                .and(activeTypeLambdaQueryWrapper -> {
                    activeTypeLambdaQueryWrapper.eq(ActiveType::getDelFlag, YesNoEnum.NO.getCode());
                    if (ObjUtil.isNotEmpty(searchKey)) {
                        activeTypeLambdaQueryWrapper.like(ActiveType::getName, searchKey);
                    }
                })
                .orderByDesc(ActiveType::getCreateTime)
        );
    }

    /**
     * 根据名称获取活动分类
     *
     * @return
     */
    public ActiveType getActiveTypeByName(String activeTypeName) {
        return activeTypeMapper.selectOne(new LambdaQueryWrapper<ActiveType>()
                .eq(ActiveType::getName, activeTypeName)
                .eq(ActiveType::getDelFlag, YesNoEnum.NO.getCode()));
    }
}
