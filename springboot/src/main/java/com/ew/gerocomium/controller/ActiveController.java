package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.service.ActiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "活动管理")
@RestController
@RequestMapping("/active")
@PreAuthorize("@AuthorityAssert.hasAuthority('/people/activity')")
public class ActiveController {
    @Resource
    private ActiveService activeService;

    @GetMapping("/getActiveType")
    @ApiOperation(value = "获取活动类型", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getActiveType(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return activeService.getActiveType();
    }

    @GetMapping("/pageActiveByKey")
    @ApiOperation(value = "分页查询活动", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageActiveByKey(@ApiParam(value = "分页查询活动请求实体", required = true) PageActiveByKeyQuery pageActiveByKeyQuery,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return activeService.pageActiveByKey(pageActiveByKeyQuery);
    }

    @GetMapping("/pageSearchElderByKey")
    @ApiOperation(value = "分页搜索老人", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageSearchElderByKey(@ApiParam(value = "分页搜索老人请求实体", required = true) PageSearchElderByKeyQuery pageSearchElderByKeyQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return activeService.pageSearchElderByKey(pageSearchElderByKeyQuery);
    }

    @PostMapping("/addActive")
    @ApiOperation(value = "新增活动", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addActive(@ApiParam(value = "新增活动请求实体", required = true) @RequestBody OperateActiveQuery operateActiveQuery,
                            @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return activeService.addActive(operateActiveQuery);
    }

    @GetMapping("/getActiveById")
    @ApiOperation(value = "根据编号查询活动", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getActiveById(@ApiParam(value = "根据编号查询活动请求参数", required = true) @RequestParam Long activeId,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return activeService.getActiveById(activeId);
    }

    @PutMapping("/editActive")
    @ApiOperation(value = "编辑活动", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editActive(@ApiParam(value = "编辑活动请求实体", required = true) @RequestBody OperateActiveQuery operateActiveQuery,
                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return activeService.editActive(operateActiveQuery);
    }

    @DeleteMapping("/deleteActive")
    @ApiOperation(value = "删除活动", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteActive(@ApiParam(value = "删除活动请求参数", required = true) @RequestParam Long activeId,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return activeService.deleteActive(activeId);
    }
}
