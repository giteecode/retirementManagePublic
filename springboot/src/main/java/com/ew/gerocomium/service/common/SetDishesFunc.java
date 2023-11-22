package com.ew.gerocomium.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.gerocomium.dao.mapper.SetDishesMapper;
import com.ew.gerocomium.dao.po.SetDishes;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐食物表公共方法
 */
@Component
public class SetDishesFunc extends ServiceImpl<SetDishesMapper, SetDishes> {
    @Resource
    private SetDishesMapper setDishesMapper;

    /**
     * 批量插入套餐食物
     *
     * @param dishesIdList
     * @param setId
     * @param editFlag
     */
    public void saveBatchSetDishes(List<Long> dishesIdList, Long setId, Boolean editFlag) {
        // 删除套餐原有菜品
        if (editFlag) {
            setDishesMapper.delete(new LambdaQueryWrapper<SetDishes>()
                    .eq(SetDishes::getSetId, setId));
        }
        // 过滤重复编号
        dishesIdList = dishesIdList.stream()
                .distinct()
                .collect(Collectors.toList());
        // 封装套餐食物实体
        List<SetDishes> setDishesList = new ArrayList<>();
        dishesIdList.forEach(dishesId -> {
            SetDishes setDishes = new SetDishes();
            setDishes.setSetId(setId);
            setDishes.setDishesId(dishesId);
            setDishesList.add(setDishes);
        });
        // 批量插入套餐食物
        saveBatch(setDishesList);
    }
}
