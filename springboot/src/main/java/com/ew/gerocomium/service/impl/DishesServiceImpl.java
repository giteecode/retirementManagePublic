package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.DishesMapper;
import com.ew.gerocomium.dao.mapper.DishesTypeMapper;
import com.ew.gerocomium.dao.po.Dishes;
import com.ew.gerocomium.dao.po.DishesType;
import com.ew.gerocomium.dao.query.OperateDishesQuery;
import com.ew.gerocomium.dao.query.OperateDishesTypeQuery;
import com.ew.gerocomium.dao.query.PageDishesByKeyQuery;
import com.ew.gerocomium.dao.vo.OperateDishesVo;
import com.ew.gerocomium.dao.vo.PageDishesByKeyVo;
import com.ew.gerocomium.service.DishesService;
import com.ew.gerocomium.service.common.DishesFunc;
import com.ew.gerocomium.service.common.DishesTypeFunc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class DishesServiceImpl implements DishesService {
    @Resource
    private DishesTypeMapper dishesTypeMapper;
    @Resource
    private DishesTypeFunc dishesTypeFunc;
    @Resource
    private DishesMapper dishesMapper;
    @Resource
    private DishesFunc dishesFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result listDishesType(String dishesTypeName) {
        return Result.success(BeanUtil.copyToList(dishesTypeFunc.listNotDelDishesType(dishesTypeName), DropDown.class));
    }

    @Override
    public Result pageDishesByKey(PageDishesByKeyQuery query) {
        // 根据搜索关键字查询菜品
        List<PageDishesByKeyVo> pageDishesByKeyVoList = dishesMapper.listDishesByKey(query);
        // 封装返回数据
        PageResult<PageDishesByKeyVo> pageResult = pageUtil.packPageResultData(pageDishesByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result addDishesType(OperateDishesTypeQuery query) {
        // 验证菜品分类是否已存在
        AssertUtil.isNull(dishesTypeFunc.getDishesTypeByName(query.getName()), ExceptionEnum.DISHES_TYPE_REPEAT);
        // 验证菜品分类是否超过总数限制
        AssertUtil.notTrue(dishesTypeFunc.checkTypeTotal(), ExceptionEnum.DISHES_TYPE_OUT);
        // 初始化菜品分类
        query.setId(null);
        DishesType dishesType = BeanUtil.toBean(query, DishesType.class);
        dishesType.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        dishesTypeMapper.insert(dishesType);
        return Result.success();
    }

    @Override
    public Result getDishesTypeById(Long dishesTypeId) {
        // 根据编号获取菜品分类
        DishesType dishesType = dishesTypeMapper.selectById(dishesTypeId);
        // 判断是否为空
        AssertUtil.notNull(dishesType, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(dishesType, DropDown.class));
    }

    @Override
    public Result editDishesType(OperateDishesTypeQuery query) {
        // 验证菜品分类是否已存在
        DishesType dishesTypeByName = dishesTypeFunc.getDishesTypeByName(query.getName());
        boolean checkName = dishesTypeByName != null && !Objects.equals(dishesTypeByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.DISHES_TYPE_REPEAT);
        // 封装修改
        DishesType dishesType = BeanUtil.toBean(query, DishesType.class);
        // 修改
        dishesTypeMapper.updateById(dishesType);
        return Result.success();
    }

    @Override
    public Result deleteDishesType(Long dishesTypeId) {
        // 验证是否存在子级菜品
        AssertUtil.notTrue(dishesFunc.checkDishes(dishesTypeId), ExceptionEnum.DISHES_NOT_NULL);
        // 封装修改
        DishesType dishesType = new DishesType();
        dishesType.setId(dishesTypeId);
        dishesType.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        dishesTypeMapper.updateById(dishesType);
        return Result.success();
    }

    @Override
    public Result addDishes(OperateDishesQuery query) {
        // 验证菜品是否存在
        AssertUtil.isNull(dishesFunc.getDishesByName(query.getName(), query.getTypeId()), ExceptionEnum.DISHES_REPEAT);
        // 初始化菜品
        query.setId(null);
        Dishes dishes = BeanUtil.toBean(query, Dishes.class);
        dishes.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        dishesMapper.insert(dishes);
        return Result.success();
    }

    @Override
    public Result getDishesById(Long dishesId) {
        // 根据编号获取菜品
        Dishes dishes = dishesMapper.selectById(dishesId);
        // 判断是否为空
        AssertUtil.notNull(dishes, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(dishes, OperateDishesVo.class));
    }

    @Override
    public Result editDishes(OperateDishesQuery query) {
        // 验证菜品是否存在
        Dishes dishesByName = dishesFunc.getDishesByName(query.getName(), query.getTypeId());
        boolean checkName = dishesByName != null && !Objects.equals(dishesByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.DISHES_REPEAT);
        // 封装修改
        Dishes dishes = BeanUtil.toBean(query, Dishes.class);
        // 修改
        dishesMapper.updateById(dishes);
        return Result.success();
    }

    @Override
    public Result deleteDishes(Long dishesId) {
        // 封装修改
        Dishes dishes = new Dishes();
        dishes.setId(dishesId);
        dishes.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        dishesMapper.updateById(dishes);
        return Result.success();
    }
}
