package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.service.OutwardService;
import com.ew.gerocomium.service.RetreatApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "外出登记")
@RestController
@RequestMapping("/outward")
@PreAuthorize("@AuthorityAssert.hasAuthority('/check-in/leave')")
public class OutwardController {
    @Resource
    private OutwardService outwardService;

    @GetMapping("/pageOutwardByKey")
    @ApiOperation(value = "分页查询外出登记", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageOutwardByKey(@ApiParam(value = "分页查询外出登记请求实体", required = true) PageOutwardByKeyQuery pageOutwardByKeyQuery,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outwardService.pageOutwardByKey(pageOutwardByKeyQuery);
    }

    @GetMapping("/pageSearchElderByKey")
    @ApiOperation(value = "分页搜索老人", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageSearchElderByKey(@ApiParam(value = "分页搜索老人请求实体", required = true) PageSearchElderByKeyQuery pageSearchElderByKeyQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outwardService.pageSearchElderByKey(pageSearchElderByKeyQuery);
    }

    @GetMapping("/pageSearchStaffByKey")
    @ApiOperation(value = "分页搜索护工", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageSearchStaffByKey(@ApiParam(value = "分页搜索护工请求实体", required = true) PageSearchStaffByKeyQuery pageSearchStaffByKeyQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outwardService.pageSearchStaffByKey(pageSearchStaffByKeyQuery);
    }

    @GetMapping("/listOutwardStaff")
    @ApiOperation(value = "获取护工列表", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listOutwardStaff(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outwardService.listOutwardStaff();
    }

    @GetMapping("/pageEmergencyContact")
    @ApiOperation(value = "分页获取紧急联系人", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageEmergencyContact(@ApiParam(value = "分页获取紧急联系人请求实体", required = true) PageSearchEmergencyContactQuery pageSearchEmergencyContactQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outwardService.pageEmergencyContact(pageSearchEmergencyContactQuery);
    }

    @GetMapping("/listContactByElderId")
    @ApiOperation(value = "获取紧急联系人列表", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listContact(@ApiParam(value = "根据编号获取外出登记请求参数", required = true) @RequestParam Long elderId,
                              @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outwardService.listContactByElderId(elderId);
    }

    @PostMapping("/addOutward")
    @ApiOperation(value = "新增外出登记", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addOutward(@ApiParam(value = "新增外出登记请求实体", required = true) @RequestBody AddOutwardQuery addOutwardQuery,
                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outwardService.addOutward(addOutwardQuery);
    }

    @GetMapping("/getOutwardById")
    @ApiOperation(value = "根据编号获取外出登记", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getOutwardById(@ApiParam(value = "根据编号获取外出登记请求参数", required = true) @RequestParam Long outwardId,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outwardService.getOutwardById(outwardId);
    }

    @PutMapping("/delayReturn")
    @ApiOperation(value = "延期返回", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result delayReturn(@ApiParam(value = "延期返回请求实体", required = true) @RequestBody DelayReturnQuery delayReturnQuery,
                              @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outwardService.delayReturn(delayReturnQuery);
    }

    @PutMapping("/recordReturn")
    @ApiOperation(value = "登记返回", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result recordReturn(@ApiParam(value = "登记返回请求实体", required = true) @RequestBody RecordReturnQuery recordReturnQuery,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outwardService.recordReturn(recordReturnQuery);
    }

    @DeleteMapping("/deleteOutward")
    @ApiOperation(value = "删除外出登记", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteOutward(@ApiParam(value = "删除外出登记请求参数", required = true) @RequestParam Long outwardId,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outwardService.deleteOutward(outwardId);
    }
}
