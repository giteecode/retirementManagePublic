package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.OperateStaffQuery;
import com.ew.gerocomium.dao.query.PageRetreatApplyQuery;
import com.ew.gerocomium.dao.query.PageStaffByKeyQuery;
import com.ew.gerocomium.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "员工管理")
@RestController
@RequestMapping("/staff")
@PreAuthorize("@AuthorityAssert.hasAuthority('/people/staff')")
public class StaffController {
    @Resource
    private StaffService staffService;

    @GetMapping("/getRole")
    @ApiOperation(value = "获取角色", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getRole(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return staffService.getRole();
    }

    @GetMapping("/pageStaffByKey")
    @ApiOperation(value = "分页查询员工", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageStaffByKey(@ApiParam(value = "分页查询员工请求实体", required = true) PageStaffByKeyQuery pageStaffByKeyQuery,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return staffService.pageStaffByKey(pageStaffByKeyQuery);
    }

    @PostMapping("/addStaff")
    @ApiOperation(value = "新增员工", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addStaff(@ApiParam(value = "新增员工请求实体", required = true) @RequestBody OperateStaffQuery operateStaffQuery,
                           @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return staffService.addStaff(operateStaffQuery);
    }

    @GetMapping("/getStaffById")
    @ApiOperation(value = "根据编号查询员工", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getStaffById(@ApiParam(value = "根据编号查询员工请求参数", required = true) @RequestParam Long staffId,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return staffService.getStaffById(staffId);
    }

    @PutMapping("/editStaff")
    @ApiOperation(value = "编辑员工", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editStaff(@ApiParam(value = "编辑员工请求实体", required = true) @RequestBody OperateStaffQuery operateStaffQuery,
                            @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return staffService.editStaff(operateStaffQuery);
    }

    @DeleteMapping("/leaveStaff")
    @ApiOperation(value = "离职员工", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result leaveStaff(@ApiParam(value = "离职员工请求参数", required = true) @RequestParam Long staffId,
                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return staffService.leaveStaff(staffId);
    }
}
