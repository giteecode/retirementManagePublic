package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.GetConsultByConsultIdAndElderIdQuery;
import com.ew.gerocomium.dao.query.OperateConsultQuery;
import com.ew.gerocomium.dao.query.PageConsultByKeyQuery;
import com.ew.gerocomium.service.ConsultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "咨询管理")
@RestController
@RequestMapping("/consult")
@PreAuthorize("@AuthorityAssert.hasAuthority('/soles/counsel')")
public class ConsultController {
    @Resource
    private ConsultService consultService;

    @GetMapping("/listConsultSource")
    @ApiOperation(value = "来源渠道", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listConsultSource(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return consultService.listConsultSource();
    }

    @GetMapping("/listConsultStaff")
    @ApiOperation(value = "接待人", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listConsultStaff(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return consultService.listConsultStaff();
    }

    @GetMapping("/pageConsultByKey")
    @ApiOperation(value = "分页查询咨询", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageConsultByKey(@ApiParam(value = "分页查询咨询请求实体", required = true) PageConsultByKeyQuery pageConsultByKeyQuery,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return consultService.pageConsultByKey(pageConsultByKeyQuery);
    }

    @PostMapping("/addConsult")
    @ApiOperation(value = "新增咨询", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addConsult(@ApiParam(value = "新增咨询请求实体", required = true) @RequestBody OperateConsultQuery operateConsultQuery,
                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return consultService.addConsult(operateConsultQuery);
    }

    @GetMapping("/getConsultByConsultIdAndElderId")
    @ApiOperation(value = "根据咨询人编号和老人编号获取咨询信息", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getConsultByConsultIdAndElderId(@ApiParam(value = "根据咨询人编号和老人编号获取咨询信息请求实体", required = true) GetConsultByConsultIdAndElderIdQuery getConsultByConsultIdAndElderIdQuery,
                                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return consultService.getConsultByConsultIdAndElderId(getConsultByConsultIdAndElderIdQuery);
    }

    @PutMapping("/editConsult")
    @ApiOperation(value = "编辑咨询", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editConsult(@ApiParam(value = "编辑咨询请求实体", required = true) @RequestBody OperateConsultQuery operateConsultQuery,
                              @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return consultService.editConsult(operateConsultQuery);
    }

    @DeleteMapping("/deleteConsult")
    @ApiOperation(value = "删除咨询", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteConsult(@ApiParam(value = "删除咨询请求参数", required = true) @RequestParam Long elderId,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return consultService.deleteConsult(elderId);
    }

    @PutMapping("/intentionConsult")
    @ApiOperation(value = "转为意向客户", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result intentionConsult(@ApiParam(value = "转为意向客户请求参数", required = true) @RequestParam Long elderId,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return consultService.intentionConsult(elderId);
    }
}
