package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.service.DepositRechargeService;
import com.ew.gerocomium.service.DishesService;
import com.ew.gerocomium.service.NurseReserveService;
import com.ew.gerocomium.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "点餐")
@RestController
@RequestMapping("/order")
@PreAuthorize("@AuthorityAssert.hasAuthority('/food/order')")
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private DepositRechargeService depositRechargeService;
    @Resource
    private DishesService dishesService;
    @Resource
    private NurseReserveService nurseReserveService;

    @GetMapping("/pageOrderByKey")
    @ApiOperation(value = "分页查询点餐", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageOrderByKey(@ApiParam(value = "分页查询点餐请求实体", required = true) PageOrderByKeyQuery pageOrderByKeyQuery,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return orderService.pageOrderByKey(pageOrderByKeyQuery);
    }

    @GetMapping("/pageSearchElderByKey")
    @ApiOperation(value = "分页搜索老人", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageSearchElderByKey(@ApiParam(value = "分页搜索老人请求实体", required = true) PageSearchElderByKeyQuery pageSearchElderByKeyQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return depositRechargeService.pageSearchElderByKey(pageSearchElderByKeyQuery);
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

    @PostMapping("/addOrder")
    @ApiOperation(value = "新增点餐", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addOrder(@ApiParam(value = "新增点餐请求实体", required = true) @RequestBody AddOrderQuery addOrderQuery,
                           @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return orderService.addOrder(addOrderQuery);
    }

    @GetMapping("/getOrderById")
    @ApiOperation(value = "根据编号获取点餐", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getOrderById(@ApiParam(value = "根据编号获取点餐请求参数", required = true) @RequestParam Long orderId,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/listNurseStaff")
    @ApiOperation(value = "护理人员", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listNurseStaff(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return nurseReserveService.listNurseStaff();
    }

    @PutMapping("/sendOrder")
    @ApiOperation(value = "送餐", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result sendOrder(@ApiParam(value = "送餐请求实体", required = true) @RequestBody SendOrderQuery sendOrderQuery,
                            @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return orderService.sendOrder(sendOrderQuery);
    }
}
