package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.service.NurseGradeService;
import com.ew.gerocomium.service.ServiceProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "护理等级")
@RestController
@RequestMapping("/nurseGrade")
@PreAuthorize("@AuthorityAssert.hasAuthority('/service/level')")
public class NurseGradeController {
    @Resource
    private NurseGradeService nurseGradeService;
    @Resource
    private ServiceProjectService serviceProjectService;

    @GetMapping("/pageNurseGradeByKey")
    @ApiOperation(value = "分页查询护理等级", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageNurseGradeByKey(@ApiParam(value = "分页查询护理等级请求实体", required = true) PageNurseGradeByKeyQuery pageNurseGradeByKeyQuery,
                                      @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return nurseGradeService.pageNurseGradeByKey(pageNurseGradeByKeyQuery);
    }

    @GetMapping("/listServiceType")
    @ApiOperation(value = "获取服务类型", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listServiceType(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return serviceProjectService.listServiceType(null);
    }

    @GetMapping("/pageServiceByKey")
    @ApiOperation(value = "分页查询服务", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageServiceByKey(@ApiParam(value = "分页查询服务请求实体", required = true) PageServiceByKeyQuery pageServiceByKeyQuery,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return nurseGradeService.pageServiceByKey(pageServiceByKeyQuery);
    }

    @PostMapping("/addNurseGrade")
    @ApiOperation(value = "新增护理等级", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addNurseGrade(@ApiParam(value = "新增护理等级请求实体", required = true) @RequestBody OperateNurseGradeQuery operateNurseGradeQuery,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return nurseGradeService.addNurseGrade(operateNurseGradeQuery);
    }

    @GetMapping("/getNurseGradeById")
    @ApiOperation(value = "根据编号查询护理等级", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getNurseGradeById(@ApiParam(value = "根据编号查询护理等级请求参数", required = true) @RequestParam Long nurseGradeId,
                                    @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return nurseGradeService.getNurseGradeById(nurseGradeId);
    }

    @PutMapping("/editNurseGrade")
    @ApiOperation(value = "编辑护理等级", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editNurseGrade(@ApiParam(value = "编辑护理等级请求实体", required = true) @RequestBody OperateNurseGradeQuery operateNurseGradeQuery,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return nurseGradeService.editNurseGrade(operateNurseGradeQuery);
    }

    @DeleteMapping("/deleteNurseGrade")
    @ApiOperation(value = "删除护理等级", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteNurseGrade(@ApiParam(value = "删除护理等级请求参数", required = true) @RequestParam Long nurseGradeId,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return nurseGradeService.deleteNurseGrade(nurseGradeId);
    }
}
