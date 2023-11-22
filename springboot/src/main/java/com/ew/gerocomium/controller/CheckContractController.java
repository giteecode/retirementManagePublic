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

@Api(tags = "入住签约")
@RestController
@RequestMapping("/checkContract")
@PreAuthorize("@AuthorityAssert.hasAuthority('/check-in/enter')")
public class CheckContractController {
    @Resource
    private CheckContractService checkContractService;
    @Resource
    private NurseGradeService nurseGradeService;
    @Resource
    private CateringSetService cateringSetService;
    @Resource
    private ConsultService consultService;

    @GetMapping("/pageCheckContractByKey")
    @ApiOperation(value = "分页查询入住签约", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageCheckContractByKey(@ApiParam(value = "分页查询入住签约请求实体", required = true) PageCheckContractByKeyQuery pageCheckContractByKeyQuery,
                                         @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return checkContractService.pageCheckContractByKey(pageCheckContractByKeyQuery);
    }

    @GetMapping("/pageSearchElderByKey")
    @ApiOperation(value = "分页搜索老人", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageSearchElderByKey(@ApiParam(value = "分页搜索老人请求实体", required = true) PageSearchElderByKeyQuery pageSearchElderByKeyQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return checkContractService.pageSearchElderByKey(pageSearchElderByKeyQuery);
    }

    @GetMapping("/listNurseGrade")
    @ApiOperation(value = "获取护理等级列表", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listNurseGrade(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return checkContractService.listNurseGrade();
    }

    @GetMapping("/getNurseGradeById")
    @ApiOperation(value = "根据编号查询护理等级", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getNurseGradeById(@ApiParam(value = "根据编号查询护理等级请求参数", required = true) @RequestParam Long nurseGradeId,
                                    @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return nurseGradeService.getNurseGradeById(nurseGradeId);
    }

    @GetMapping("/listCateringSet")
    @ApiOperation(value = "获取餐饮套餐列表", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listCateringSet(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return checkContractService.listCateringSet();
    }

    @GetMapping("/getCateringSetById")
    @ApiOperation(value = "根据编号查询餐饮套餐", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getCateringSetById(@ApiParam(value = "根据编号查询餐饮套餐请求参数", required = true) @RequestParam Long cateringSetId,
                                     @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return cateringSetService.getCateringSetById(cateringSetId);
    }

    @GetMapping("/getBuildTree")
    @ApiOperation(value = "获取楼栋树", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getBuildTree(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return checkContractService.getBuildTree();
    }

    @GetMapping("/getBedById")
    @ApiOperation(value = "根据编号查询床位", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getBedById(@ApiParam(value = "根据编号查询床位请求参数", required = true) @RequestParam Long bedId,
                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return checkContractService.getBedById(bedId);
    }

    @GetMapping("/listReserveStaff")
    @ApiOperation(value = "获取营销人员", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listReserveStaff(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return consultService.listConsultStaff();
    }

    @PostMapping("/addCheckContract")
    @ApiOperation(value = "新增入住签约", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addCheckContract(@ApiParam(value = "新增入住签约请求实体", required = true) @RequestBody OperateCheckContractQuery operateCheckContractQuery,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return checkContractService.addCheckContract(operateCheckContractQuery);
    }

    @GetMapping("/getCheckContractById")
    @ApiOperation(value = "根据老人编号查询入住签约", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getCheckContractById(@ApiParam(value = "根据老人编号查询入住签约请求参数", required = true) @RequestParam Long elderId,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return checkContractService.getCheckContractById(elderId);
    }

    @PutMapping("/editCheckContract")
    @ApiOperation(value = "编辑入住签约", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editCheckContract(@ApiParam(value = "编辑入住签约请求实体", required = true) @RequestBody OperateCheckContractQuery operateCheckContractQuery,
                                    @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return checkContractService.editCheckContract(operateCheckContractQuery);
    }

    @DeleteMapping("/deleteCheckContract")
    @ApiOperation(value = "删除入住签约", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteCheckContract(@ApiParam(value = "删除入住签约请求参数", required = true) @RequestParam Long elderId,
                                      @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return checkContractService.deleteCheckContract(elderId);
    }
}
