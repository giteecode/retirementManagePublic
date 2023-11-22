package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.OperateSourceQuery;
import com.ew.gerocomium.dao.query.PageSourceByKeyQuery;
import com.ew.gerocomium.service.SourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "来源渠道")
@RestController
@RequestMapping("/source")
@PreAuthorize("@AuthorityAssert.hasAuthority('/base/marketing/origin')")
public class SourceController {
    @Resource
    private SourceService sourceService;

    @GetMapping("/pageSourceByKey")
    @ApiOperation(value = "分页查询来源渠道", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageSourceByKey(@ApiParam(value = "分页查询来源渠道请求实体", required = true) PageSourceByKeyQuery pageSourceByKeyQuery,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return sourceService.pageSourceByKey(pageSourceByKeyQuery);
    }

    @PostMapping("/addSource")
    @ApiOperation(value = "新增来源渠道", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addSource(@ApiParam(value = "新增来源渠道请求参数", required = true) @RequestBody OperateSourceQuery operateSourceQuery,
                            @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return sourceService.addSource(operateSourceQuery.getName());
    }

    @GetMapping("/getSourceById")
    @ApiOperation(value = "根据编号获取来源渠道", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getSourceById(@ApiParam(value = "根据编号获取来源渠道请求参数", required = true) @RequestParam Long sourceId,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return sourceService.getSourceById(sourceId);
    }

    @PutMapping("/editSource")
    @ApiOperation(value = "编辑来源渠道", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editSource(@ApiParam(value = "编辑来源渠道请求实体", required = true) @RequestBody OperateSourceQuery operateSourceQuery,
                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return sourceService.editSource(operateSourceQuery);
    }

    @DeleteMapping("/deleteSource")
    @ApiOperation(value = "删除来源渠道", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteSource(@ApiParam(value = "删除来源渠道请求参数", required = true) @RequestParam Long sourceId,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return sourceService.deleteSource(sourceId);
    }
}
