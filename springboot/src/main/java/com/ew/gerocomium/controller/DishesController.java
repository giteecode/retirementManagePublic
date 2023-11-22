package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.OperateDishesQuery;
import com.ew.gerocomium.dao.query.OperateDishesTypeQuery;
import com.ew.gerocomium.dao.query.PageDishesByKeyQuery;
import com.ew.gerocomium.service.DishesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "菜品管理")
@RestController
@RequestMapping("/dishes")
@PreAuthorize("@AuthorityAssert.hasAuthority('/food/dish')")
public class DishesController {
    @Resource
    private DishesService dishesService;

    @GetMapping("/listDishesType")
    @ApiOperation(value = "获取菜品分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listDishesType(@ApiParam(value = "获取菜品分类请求参数", required = false) String dishesTypeName,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return dishesService.listDishesType(dishesTypeName);
    }

    @GetMapping("/pageDishesByKey")
    @ApiOperation(value = "分页查询菜品", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageDishesByKey(@ApiParam(value = "分页查询菜品请求实体", required = true) PageDishesByKeyQuery pageDishesByKeyQuery,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return dishesService.pageDishesByKey(pageDishesByKeyQuery);
    }

    @PostMapping("/addDishesType")
    @ApiOperation(value = "新增菜品分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addDishesType(@ApiParam(value = "新增菜品分类请求实体", required = true) @RequestBody OperateDishesTypeQuery operateDishesTypeQuery,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return dishesService.addDishesType(operateDishesTypeQuery);
    }

    @GetMapping("/getDishesTypeById")
    @ApiOperation(value = "根据编号查询菜品分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getDishesTypeById(@ApiParam(value = "根据编号查询菜品分类请求参数", required = true) @RequestParam Long dishesTypeId,
                                    @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return dishesService.getDishesTypeById(dishesTypeId);
    }

    @PutMapping("/editDishesType")
    @ApiOperation(value = "编辑菜品分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editDishesType(@ApiParam(value = "编辑菜品分类请求实体", required = true) @RequestBody OperateDishesTypeQuery operateDishesTypeQuery,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return dishesService.editDishesType(operateDishesTypeQuery);
    }

    @DeleteMapping("/deleteDishesType")
    @ApiOperation(value = "删除菜品分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteDishesType(@ApiParam(value = "删除菜品分类请求参数", required = true) @RequestParam Long dishesTypeId,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return dishesService.deleteDishesType(dishesTypeId);
    }

    @PostMapping("/addDishes")
    @ApiOperation(value = "新增菜品", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addDishes(@ApiParam(value = "新增菜品请求实体", required = true) @RequestBody OperateDishesQuery operateDishesQuery,
                            @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return dishesService.addDishes(operateDishesQuery);
    }

    @GetMapping("/getDishesById")
    @ApiOperation(value = "根据编号查询菜品", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getDishesById(@ApiParam(value = "根据编号查询菜品请求参数", required = true) @RequestParam Long dishesId,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return dishesService.getDishesById(dishesId);
    }

    @PutMapping("/editDishes")
    @ApiOperation(value = "编辑菜品", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editDishes(@ApiParam(value = "编辑菜品请求实体", required = true) @RequestBody OperateDishesQuery operateDishesQuery,
                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return dishesService.editDishes(operateDishesQuery);
    }

    @DeleteMapping("/deleteDishes")
    @ApiOperation(value = "删除菜品", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteDishes(@ApiParam(value = "删除菜品请求参数", required = true) @RequestParam Long dishesId,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return dishesService.deleteDishes(dishesId);
    }
}
