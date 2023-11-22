package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.OperateCateringSetQuery;
import com.ew.gerocomium.dao.query.PageCateringSetByKeyQuery;
import com.ew.gerocomium.dao.query.PageDishesByKeyQuery;
import com.ew.gerocomium.service.CateringSetService;
import com.ew.gerocomium.service.DishesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "餐饮套餐")
@RestController
@RequestMapping("/cateringSet")
@PreAuthorize("@AuthorityAssert.hasAuthority('/food/list')")
public class CateringSetController {
    @Resource
    private CateringSetService cateringSetService;
    @Resource
    private DishesService dishesService;

    @GetMapping("/pageCateringSetByKey")
    @ApiOperation(value = "分页查询餐饮套餐", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageCateringSetByKey(@ApiParam(value = "分页查询餐饮套餐请求实体", required = true) PageCateringSetByKeyQuery pageCateringSetByKeyQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return cateringSetService.pageCateringSetByKey(pageCateringSetByKeyQuery);
    }

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

    @PostMapping("/addCateringSet")
    @ApiOperation(value = "新增餐饮套餐", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addCateringSet(@ApiParam(value = "新增餐饮套餐请求实体", required = true) @RequestBody OperateCateringSetQuery operateCateringSetQuery,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return cateringSetService.addCateringSet(operateCateringSetQuery);
    }

    @GetMapping("/getCateringSetById")
    @ApiOperation(value = "根据编号查询餐饮套餐", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getCateringSetById(@ApiParam(value = "根据编号查询餐饮套餐请求参数", required = true) @RequestParam Long setId,
                                     @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return cateringSetService.getCateringSetById(setId);
    }

    @PutMapping("/editCateringSet")
    @ApiOperation(value = "编辑餐饮套餐", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editCateringSet(@ApiParam(value = "编辑餐饮套餐请求实体", required = true) @RequestBody OperateCateringSetQuery operateCateringSetQuery,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return cateringSetService.editCateringSet(operateCateringSetQuery);
    }

    @DeleteMapping("/deleteCateringSet")
    @ApiOperation(value = "删除餐饮套餐", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteCateringSet(@ApiParam(value = "删除餐饮套餐请求参数", required = true) @RequestParam Long setId,
                                    @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return cateringSetService.deleteCateringSet(setId);
    }
}
