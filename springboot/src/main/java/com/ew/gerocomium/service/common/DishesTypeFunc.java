package com.ew.gerocomium.service.common;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.mapper.DishesTypeMapper;
import com.ew.gerocomium.dao.po.DishesType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜品类别表公共方法
 */
@Component
public class DishesTypeFunc {
    @Resource
    private DishesTypeMapper dishesTypeMapper;

    /**
     * 获取未被删除的菜品分类
     *
     * @return
     */
    public List<DishesType> listNotDelDishesType(String dishesTypeName) {
        return dishesTypeMapper.selectList(new LambdaQueryWrapper<DishesType>()
                .and(dishesTypeLambdaQueryWrapper -> {
                    dishesTypeLambdaQueryWrapper
                            .eq(DishesType::getDelFlag, YesNoEnum.NO.getCode());
                    if(ObjUtil.isNotEmpty(dishesTypeName)){
                        dishesTypeLambdaQueryWrapper
                                .like(DishesType::getName, dishesTypeName);
                    }
                })
        );
    }

    /**
     * 根据菜品分类名称获取菜品分类
     *
     * @return
     */
    public DishesType getDishesTypeByName(String typeName) {
        return dishesTypeMapper.selectOne(new LambdaQueryWrapper<DishesType>()
                .eq(DishesType::getName, typeName)
                .eq(DishesType::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 判断分类是否超过总数限制
     *
     * @return
     */
    public Boolean checkTypeTotal() {
        Long typeTotal = dishesTypeMapper.selectCount(new LambdaQueryWrapper<DishesType>()
                .eq(DishesType::getDelFlag, YesNoEnum.NO.getCode()));
        return typeTotal >= Constant.TOTAL_LIMIT;
    }
}
