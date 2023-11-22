package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "事故登记")
@RestController
@RequestMapping("/accident")
@PreAuthorize("@AuthorityAssert.hasAuthority('/check-in/accident')")
public class AccidentController {
    @Resource
    private AccidentService accidentService;
    @Resource
    private DepositRechargeService depositRechargeService;
    @Resource
    private NurseReserveService nurseReserveService;
    @Resource
    private OutwardService outwardService;

    @GetMapping("/pageAccidentByKey")
    @ApiOperation(value = "分页查询事故登记", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageAccidentByKey(@ApiParam(value = "分页查询事故登记请求实体", required = true) PageAccidentByKeyQuery pageAccidentByKeyQuery,
                                    @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return accidentService.pageAccidentByKey(pageAccidentByKeyQuery);
    }

    @GetMapping("/pageSearchElderByKey")
    @ApiOperation(value = "分页搜索老人", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageSearchElderByKey(@ApiParam(value = "分页搜索老人请求实体", required = true) PageSearchElderByKeyQuery pageSearchElderByKeyQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return depositRechargeService.pageSearchElderByKey(pageSearchElderByKeyQuery);
    }

    @GetMapping("/pageSearchStaffByKey")
    @ApiOperation(value = "分页搜索护工", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageSearchStaffByKey(@ApiParam(value = "分页搜索护工请求实体", required = true) PageSearchStaffByKeyQuery pageSearchStaffByKeyQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outwardService.pageSearchStaffByKey(pageSearchStaffByKeyQuery);
    }

    @GetMapping("/listAccidentStaff")
    @ApiOperation(value = "获取护工列表", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listOutwardStaff(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return nurseReserveService.listNurseStaff();
    }

    @PostMapping("/addAccident")
    @ApiOperation(value = "新增事故登记", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addOutward(@ApiParam(value = "新增事故登记请求实体", required = true) @RequestBody AddAccidentQuery addAccidentQuery,
                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return accidentService.addAccident(addAccidentQuery);
    }

    @GetMapping("/getAccidentById")
    @ApiOperation(value = "根据编号获取事故登记", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getAccidentById(@ApiParam(value = "根据编号获取事故登记请求参数", required = true) @RequestParam Long accidentId,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return accidentService.getAccidentById(accidentId);
    }

    @PutMapping("/editAccident")
    @ApiOperation(value = "编辑事故登记", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editAccident(@ApiParam(value = "编辑事故登记请求实体", required = true) @RequestBody EditAccidentQuery editAccidentQuery,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return accidentService.editAccident(editAccidentQuery);
    }

    @DeleteMapping("/deleteAccident")
    @ApiOperation(value = "删除事故登记", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteAccident(@ApiParam(value = "删除事故登记请求参数", required = true) @RequestParam Long accidentId,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return accidentService.deleteAccident(accidentId);
    }
}
