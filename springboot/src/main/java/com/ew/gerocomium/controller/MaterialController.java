package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.service.MaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "物资信息")
@RestController
@RequestMapping("/material")
@PreAuthorize("@AuthorityAssert.hasAuthority('/resource/info')")
public class MaterialController {
    @Resource
    private MaterialService materialService;

    @GetMapping("/getMaterialType")
    @ApiOperation(value = "获取物资分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getMaterialType(@ApiParam(value = "获取物资分类请求参数", required = false) String materialTypeName,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return materialService.getMaterialType(materialTypeName);
    }

    @GetMapping("/pageMaterialByKey")
    @ApiOperation(value = "分页查询物资", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageMaterialByKey(@ApiParam(value = "分页查询物资请求实体", required = true) PageMaterialByKeyQuery pageMaterialByKeyQuery,
                                    @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return materialService.pageMaterialByKey(pageMaterialByKeyQuery);
    }

    @PostMapping("/addMaterialType")
    @ApiOperation(value = "新增物资分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addMaterialType(@ApiParam(value = "新增物资分类请求实体", required = true) @RequestBody OperateMaterialTypeQuery operateMaterialTypeQuery,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return materialService.addMaterialType(operateMaterialTypeQuery);
    }

    @GetMapping("/getMaterialTypeById")
    @ApiOperation(value = "根据编号查询物资分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getMaterialTypeById(@ApiParam(value = "根据编号查询物资分类请求参数", required = true) @RequestParam Long materialTypeId,
                                      @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return materialService.getMaterialTypeById(materialTypeId);
    }

    @PutMapping("/editMaterialType")
    @ApiOperation(value = "编辑物资分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editMaterialType(@ApiParam(value = "编辑物资分类请求实体", required = true) @RequestBody OperateMaterialTypeQuery operateMaterialTypeQuery,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return materialService.editMaterialType(operateMaterialTypeQuery);
    }

    @DeleteMapping("/deleteMaterialType")
    @ApiOperation(value = "删除物资分类", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteMaterialType(@ApiParam(value = "删除物资分类请求参数", required = true) @RequestParam Long materialTypeId,
                                     @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return materialService.deleteMaterialType(materialTypeId);
    }

    @PostMapping("/addMaterial")
    @ApiOperation(value = "新增物资", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addMaterial(@ApiParam(value = "新增物资请求实体", required = true) @RequestBody OperateMaterialQuery operateMaterialQuery,
                              @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return materialService.addMaterial(operateMaterialQuery);
    }

    @GetMapping("/getMaterialById")
    @ApiOperation(value = "根据编号查询物资", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getMaterialById(@ApiParam(value = "根据编号查询物资请求参数", required = true) @RequestParam Long materialId,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return materialService.getMaterialById(materialId);
    }

    @PutMapping("/editMaterial")
    @ApiOperation(value = "编辑物资", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editMaterial(@ApiParam(value = "编辑物资请求实体", required = true) @RequestBody OperateMaterialQuery operateMaterialQuery,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return materialService.editMaterial(operateMaterialQuery);
    }

    @DeleteMapping("/deleteMaterial")
    @ApiOperation(value = "删除物资", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteMaterial(@ApiParam(value = "删除物资请求参数", required = true) @RequestParam Long materialId,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return materialService.deleteMaterial(materialId);
    }
}
