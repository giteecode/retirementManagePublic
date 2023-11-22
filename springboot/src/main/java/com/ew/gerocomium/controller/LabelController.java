package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.OperateLabelQuery;
import com.ew.gerocomium.dao.query.OperateLabelTypeQuery;
import com.ew.gerocomium.service.LabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "客户标签")
@RestController
@RequestMapping("/label")
@PreAuthorize("@AuthorityAssert.hasAuthority('/base/marketing/tag')")
public class LabelController {
    @Resource
    private LabelService labelService;

    @GetMapping("/listLabel")
    @ApiOperation(value = "获取客户标签列表", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listLabel(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return labelService.listLabel();
    }

    @PostMapping("/addLabelType")
    @ApiOperation(value = "新增标签分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addLabelType(@ApiParam(value = "新增标签分类请求实体", required = true) @RequestBody OperateLabelTypeQuery operateLabelTypeQuery,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return labelService.addLabelType(operateLabelTypeQuery);
    }

    @GetMapping("/getLabelTypeById")
    @ApiOperation(value = "根据编号获取标签分类信息", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getLabelTypeById(@ApiParam(value = "根据编号获取标签分类信息请求参数", required = true) @RequestParam Long typeId,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return labelService.getLabelTypeById(typeId);
    }

    @PutMapping("/editLabelType")
    @ApiOperation(value = "编辑标签分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editLabelType(@ApiParam(value = "编辑标签分类请求实体", required = true) @RequestBody OperateLabelTypeQuery operateLabelTypeQuery,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return labelService.editLabelType(operateLabelTypeQuery);
    }

    @DeleteMapping("/deleteLabelType")
    @ApiOperation(value = "删除标签分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteLabelType(@ApiParam(value = "删除标签分类请求参数", required = true) @RequestParam Long typeId,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return labelService.deleteLabelType(typeId);
    }

    @PostMapping("/addLabel")
    @ApiOperation(value = "新增标签", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addLabel(@ApiParam(value = "新增标签请求实体", required = true) @RequestBody OperateLabelQuery operateLabelQuery,
                           @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return labelService.addLabel(operateLabelQuery);
    }

    @GetMapping("/getLabelById")
    @ApiOperation(value = "根据编号获取标签信息", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getLabelById(@ApiParam(value = "根据编号获取标签信息请求参数", required = true) @RequestParam Long labelId,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return labelService.getLabelById(labelId);
    }

    @PutMapping("/editLabel")
    @ApiOperation(value = "编辑标签", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editLabel(@ApiParam(value = "编辑标签请求实体", required = true) @RequestBody OperateLabelQuery operateLabelQuery,
                            @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return labelService.editLabel(operateLabelQuery);
    }

    @DeleteMapping("/deleteLabel")
    @ApiOperation(value = "删除标签", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteLabel(@ApiParam(value = "删除标签请求参数", required = true) @RequestParam Long labelId,
                              @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return labelService.deleteLabel(labelId);
    }
}
