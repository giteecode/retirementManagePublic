package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.service.ServiceProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "服务项目")
@RestController
@RequestMapping("/service")
@PreAuthorize("@AuthorityAssert.hasAuthority('/service/project')")
public class ServiceProjectController {
    @Resource
    private ServiceProjectService serviceProjectService;

    @GetMapping("/getServiceType")
    @ApiOperation(value = "获取服务类型", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getServiceType(@ApiParam(value = "获取服务类型请求参数", required = false) String serviceTypeName,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return serviceProjectService.listServiceType(serviceTypeName);
    }

    @GetMapping("/pageServiceByKey")
    @ApiOperation(value = "分页查询服务", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageServiceByKey(@ApiParam(value = "分页查询服务请求实体", required = true) PageServiceByKeyQuery pageServiceByKeyQuery,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return serviceProjectService.pageServiceByKey(pageServiceByKeyQuery);
    }

    @PostMapping("/addServiceType")
    @ApiOperation(value = "新增服务类型", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addServiceType(@ApiParam(value = "新增服务类型请求实体", required = true) @RequestBody OperateServiceTypeQuery operateServiceTypeQuery,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return serviceProjectService.addServiceType(operateServiceTypeQuery);
    }

    @GetMapping("/getServiceTypeById")
    @ApiOperation(value = "根据编号查询服务类型", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getServiceTypeById(@ApiParam(value = "根据编号查询服务类型请求参数", required = true) @RequestParam Long serviceTypeId,
                                     @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return serviceProjectService.getServiceTypeById(serviceTypeId);
    }

    @PutMapping("/editServiceType")
    @ApiOperation(value = "编辑服务类型", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editServiceType(@ApiParam(value = "编辑服务类型请求实体", required = true) @RequestBody OperateServiceTypeQuery operateServiceTypeQuery,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return serviceProjectService.editServiceType(operateServiceTypeQuery);
    }

    @DeleteMapping("/deleteServiceType")
    @ApiOperation(value = "删除服务类型", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteServiceType(@ApiParam(value = "删除服务类型请求参数", required = true) @RequestParam Long serviceTypeId,
                                    @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return serviceProjectService.deleteServiceType(serviceTypeId);
    }

    @PostMapping("/addService")
    @ApiOperation(value = "新增服务", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addService(@ApiParam(value = "新增服务请求实体", required = true) @RequestBody OperateServiceQuery operateServiceQuery,
                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return serviceProjectService.addService(operateServiceQuery);
    }

    @GetMapping("/getServiceById")
    @ApiOperation(value = "根据编号查询服务", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getServiceById(@ApiParam(value = "根据编号查询服务请求参数", required = true) @RequestParam Long serviceId,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return serviceProjectService.getServiceById(serviceId);
    }

    @PutMapping("/editService")
    @ApiOperation(value = "编辑服务", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editService(@ApiParam(value = "编辑服务请求实体", required = true) @RequestBody OperateServiceQuery operateServiceQuery,
                              @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return serviceProjectService.editService(operateServiceQuery);
    }

    @DeleteMapping("/deleteService")
    @ApiOperation(value = "删除服务", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteService(@ApiParam(value = "删除服务请求参数", required = true) @RequestParam Long serviceId,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return serviceProjectService.deleteService(serviceId);
    }
}
