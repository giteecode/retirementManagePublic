package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.service.DepositRechargeService;
import com.ew.gerocomium.service.NurseReserveService;
import com.ew.gerocomium.service.ServiceProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "服务预定")
@RestController
@RequestMapping("/nurseReserve")
@PreAuthorize("@AuthorityAssert.hasAuthority('/service/book')")
public class NurseReserveController {
    @Resource
    private NurseReserveService nurseReserveService;
    @Resource
    private DepositRechargeService depositRechargeService;
    @Resource
    private ServiceProjectService serviceProjectService;

    @GetMapping("/pageNurseReserveByKey")
    @ApiOperation(value = "分页查询护理预定", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageNurseReserveByKey(@ApiParam(value = "分页查询护理预定请求实体", required = true) PageNurseReserveByKeyQuery PageNurseReserveByKeyQuery,
                                        @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return nurseReserveService.pageNurseReserveByKey(PageNurseReserveByKeyQuery);
    }

    @GetMapping("/pageSearchElderByKey")
    @ApiOperation(value = "分页搜索老人", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageSearchElderByKey(@ApiParam(value = "分页搜索老人请求实体", required = true) PageSearchElderByKeyQuery pageSearchElderByKeyQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return depositRechargeService.pageSearchElderByKey(pageSearchElderByKeyQuery);
    }

    @GetMapping("/listService")
    @ApiOperation(value = "获取服务项目", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listService(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return nurseReserveService.listService();
    }

    @GetMapping("/getServiceById")
    @ApiOperation(value = "根据编号查询服务", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getServiceById(@ApiParam(value = "根据编号查询服务请求参数", required = true) @RequestParam Long serviceId,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return serviceProjectService.getServiceById(serviceId);
    }

    @PostMapping("/addNurseReserve")
    @ApiOperation(value = "新增护理预定", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addNurseReserve(@ApiParam(value = "新增护理预定请求实体", required = true) @RequestBody AddNurseReserveQuery addNurseReserveQuery,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return nurseReserveService.addNurseReserve(addNurseReserveQuery);
    }

    @GetMapping("/listNurseStaff")
    @ApiOperation(value = "护理人员", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listNurseStaff(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return nurseReserveService.listNurseStaff();
    }

    @PutMapping("/executeNurseReserve")
    @ApiOperation(value = "执行护理预定", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result executeNurseReserve(@ApiParam(value = "执行护理预定请求实体", required = true) @RequestBody ExecuteNurseReserveQuery executeNurseReserve,
                                      @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return nurseReserveService.executeNurseReserve(executeNurseReserve);
    }
}
