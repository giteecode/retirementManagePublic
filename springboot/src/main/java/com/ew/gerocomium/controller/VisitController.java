package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.service.DepositRechargeService;
import com.ew.gerocomium.service.VisitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "来访登记")
@RestController
@RequestMapping("/visit")
@PreAuthorize("@AuthorityAssert.hasAuthority('/check-in/visit')")
public class VisitController {
    @Resource
    private VisitService visitService;
    @Resource
    private DepositRechargeService depositRechargeService;

    @GetMapping("/pageVisitByKey")
    @ApiOperation(value = "分页查询来访登记", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageVisitByKey(@ApiParam(value = "分页查询来访登记请求实体", required = true) PageVisitByKeyQuery pageVisitByKeyQuery,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return visitService.pageVisitByKey(pageVisitByKeyQuery);
    }

    @GetMapping("/pageSearchElderByKey")
    @ApiOperation(value = "分页搜索老人", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageSearchElderByKey(@ApiParam(value = "分页搜索老人请求实体", required = true) PageSearchElderByKeyQuery pageSearchElderByKeyQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return depositRechargeService.pageSearchElderByKey(pageSearchElderByKeyQuery);
    }

    @PostMapping("/addVisit")
    @ApiOperation(value = "新增来访登记", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addOutward(@ApiParam(value = "新增来访登记请求实体", required = true) @RequestBody AddVisitQuery addVisitQuery,
                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return visitService.addVisit(addVisitQuery);
    }

    @GetMapping("/getVisitById")
    @ApiOperation(value = "根据编号获取来访登记", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getVisitById(@ApiParam(value = "根据编号获取来访登记请求参数", required = true) @RequestParam Long visitId,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return visitService.getVisitById(visitId);
    }

    @PutMapping("/editVisit")
    @ApiOperation(value = "编辑来访登记", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editVisit(@ApiParam(value = "编辑来访登记请求实体", required = true) @RequestBody EditVisitQuery editVisitQuery,
                            @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return visitService.editVisit(editVisitQuery);
    }

    @PutMapping("/recordLeave")
    @ApiOperation(value = "登记离开", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result recordLeave(@ApiParam(value = "登记离开请求实体", required = true) @RequestBody RecordLeaveQuery recordLeaveQuery,
                              @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return visitService.recordLeave(recordLeaveQuery);
    }

    @DeleteMapping("/deleteVisit")
    @ApiOperation(value = "删除来访登记", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteVisit(@ApiParam(value = "删除来访登记请求参数", required = true) @RequestParam Long visitId,
                              @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return visitService.deleteVisit(visitId);
    }
}
