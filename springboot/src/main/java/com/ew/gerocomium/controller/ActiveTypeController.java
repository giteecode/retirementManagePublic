package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.OperateActiveTypeQuery;
import com.ew.gerocomium.dao.query.PageActiveTypeByKeyQuery;
import com.ew.gerocomium.service.ActiveTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "活动分类")
@RestController
@RequestMapping("/activeType")
@PreAuthorize("@AuthorityAssert.hasAuthority('/base/activity')")
public class ActiveTypeController {
    @Resource
    private ActiveTypeService activeTypeService;

    @GetMapping("/pageActiveTypeByKey")
    @ApiOperation(value = "分页查询活动分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageActiveTypeByKey(@ApiParam(value = "分页查询活动分类请求实体", required = true) PageActiveTypeByKeyQuery pageActiveTypeByKeyQuery,
                                      @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return activeTypeService.pageActiveTypeByKey(pageActiveTypeByKeyQuery);
    }

    @PostMapping("/addActiveType")
    @ApiOperation(value = "新增活动分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addActiveType(@ApiParam(value = "新增活动分类请求参数", required = true) @RequestParam String activeTypeName,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return activeTypeService.addActiveType(activeTypeName);
    }

    @GetMapping("/getActiveTypeById")
    @ApiOperation(value = "根据编号获取活动分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getActiveTypeById(@ApiParam(value = "根据编号获取活动分类请求参数", required = true) @RequestParam Long activeTypeId,
                                    @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return activeTypeService.getActiveTypeById(activeTypeId);
    }

    @PutMapping("/editActiveType")
    @ApiOperation(value = "编辑活动分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editActiveType(@ApiParam(value = "编辑活动分类请求实体", required = true) @RequestBody OperateActiveTypeQuery operateActiveTypeQuery,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return activeTypeService.editActiveType(operateActiveTypeQuery);
    }

    @DeleteMapping("/deleteActiveType")
    @ApiOperation(value = "删除活动分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteActiveType(@ApiParam(value = "删除活动分类请求参数", required = true) @RequestParam Long activeTypeId,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return activeTypeService.deleteActiveType(activeTypeId);
    }
}
