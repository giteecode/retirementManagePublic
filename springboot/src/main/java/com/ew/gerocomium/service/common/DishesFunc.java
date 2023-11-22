package com.ew.gerocomium.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.mapper.DishesMapper;
import com.ew.gerocomium.dao.po.Dishes;
import com.ew.gerocomium.dao.po.ServiceItem;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜品表公共方法
 */
@Component
public class DishesFunc {
    @Resource
    private DishesMapper dishesMapper;

    /**
     * 判断该分类下是否存在子级菜品
     *
     * @param typeId
     * @return
     */
    public Boolean checkDishes(Long typeId) {
        List<Dishes> dishesList = dishesMapper.selectList(new LambdaQueryWrapper<Dishes>()
                .eq(Dishes::getTypeId, typeId)
                .eq(Dishes::getDelFlag, YesNoEnum.NO.getCode()));
        return dishesList.size() > 0;
    }

    /**
     * 根据菜品名称查询菜品
     *
     * @param dishesName
     * @param typeId
     * @return
     */
    public Dishes getDishesByName(String dishesName, Long typeId) {
        return dishesMapper.selectOne(new LambdaQueryWrapper<Dishes>()
                .eq(Dishes::getName, dishesName)
                .eq(Dishes::getTypeId, typeId)
                .eq(Dishes::getDelFlag, YesNoEnum.NO.getCode()));
    }
}
