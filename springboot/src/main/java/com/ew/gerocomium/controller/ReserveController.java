package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.service.ConsultService;
import com.ew.gerocomium.service.ReserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "预定管理")
@RestController
@RequestMapping("/reserve")
@PreAuthorize("@AuthorityAssert.hasAuthority('/soles/booking')")
public class ReserveController {
    @Resource
    private ReserveService reserveService;
    @Resource
    private ConsultService consultService;

    @GetMapping("/listReserveStaff")
    @ApiOperation(value = "获取营销人员", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listReserveStaff(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return consultService.listConsultStaff();
    }

    @GetMapping("/pageReserveByKey")
    @ApiOperation(value = "分页查询预定", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageReserveByKey(@ApiParam(value = "分页查询预定请求实体", required = true) PageReserveByKeyQuery pageReserveByKeyQuery,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return reserveService.pageReserveByKey(pageReserveByKeyQuery);
    }

    @GetMapping("/pageSearchElderByKey")
    @ApiOperation(value = "分页搜索老人", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageSearchElderByKey(@ApiParam(value = "分页搜索老人请求实体", required = true) PageSearchElderByKeyQuery pageSearchElderByKeyQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return reserveService.pageSearchElderByKey(pageSearchElderByKeyQuery);
    }

    @GetMapping("/getBuildTree")
    @ApiOperation(value = "获取楼栋树", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getBuildTree(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return reserveService.getBuildTree();
    }

    @PostMapping("/addReserve")
    @ApiOperation(value = "新增预定", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addReserve(@ApiParam(value = "新增预定请求实体", required = true) @RequestBody AddReserveQuery addReserveQuery,
                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return reserveService.addReserve(addReserveQuery);
    }

    @GetMapping("/getReserveByReserveIdAndElderId")
    @ApiOperation(value = "根据预定编号和老人编号获取预定信息", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getReserveByReserveIdAndElderId(@ApiParam(value = "根据预定编号和老人编号获取预定信息请求实体", required = true) GetReserveByReserveIdAndElderIdQuery getReserveByReserveIdAndElderIdQuery,
                                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return reserveService.getReserveByReserveIdAndElderId(getReserveByReserveIdAndElderIdQuery);
    }

    @PutMapping("/refund")
    @ApiOperation(value = "退款", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result refund(@ApiParam(value = "退款请求参数", required = true) @RequestBody GetReserveByReserveIdAndElderIdQuery getReserveByReserveIdAndElderIdQuery,
                         @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return reserveService.refund(getReserveByReserveIdAndElderIdQuery.getReserveId());
    }
}
