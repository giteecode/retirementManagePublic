package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.OperateDishesQuery;
import com.ew.gerocomium.dao.query.OperateDishesTypeQuery;
import com.ew.gerocomium.dao.query.PageDishesByKeyQuery;

public interface DishesService {
    /**
     * 获取菜品分类
     *
     * @return
     */
    Result listDishesType(String dishesTypeName);

    /**
     * 分页查询菜品
     *
     * @param pageDishesByKeyQuery
     * @return
     */
    Result pageDishesByKey(PageDishesByKeyQuery pageDishesByKeyQuery);

    /**
     * 新增菜品分类
     *
     * @param operateDishesTypeQuery
     * @return
     */
    Result addDishesType(OperateDishesTypeQuery operateDishesTypeQuery);

    /**
     * 根据编号查询菜品分类
     *
     * @param dishesTypeId
     * @return
     */
    Result getDishesTypeById(Long dishesTypeId);

    /**
     * 编辑菜品分类
     *
     * @param operateDishesTypeQuery
     * @return
     */
    Result editDishesType(OperateDishesTypeQuery operateDishesTypeQuery);

    /**
     * 删除菜品分类
     *
     * @param dishesTypeId
     * @return
     */
    Result deleteDishesType(Long dishesTypeId);

    /**
     * 新增菜品
     *
     * @param operateDishesQuery
     * @return
     */
    Result addDishes(OperateDishesQuery operateDishesQuery);

    /**
     * 根据编号查询菜品
     *
     * @param dishesId
     * @return
     */
    Result getDishesById(Long dishesId);

    /**
     * 编辑菜品
     *
     * @param operateDishesQuery
     * @return
     */
    Result editDishes(OperateDishesQuery operateDishesQuery);

    /**
     * 删除菜品
     *
     * @param dishesId
     * @return
     */
    Result deleteDishes(Long dishesId);
}

