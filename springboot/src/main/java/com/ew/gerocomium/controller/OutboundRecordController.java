package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.service.DepositRechargeService;
import com.ew.gerocomium.service.OutboundRecordService;
import com.ew.gerocomium.service.WarehouseRecordService;
import com.ew.gerocomium.service.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "出库管理")
@RestController
@RequestMapping("/outboundRecord")
@PreAuthorize("@AuthorityAssert.hasAuthority('/resource/leave')")
public class OutboundRecordController {
    @Resource
    private OutboundRecordService outboundRecordService;
    @Resource
    private DepositRechargeService depositRechargeService;
    @Resource
    private WarehouseService warehouseService;
    @Resource
    private WarehouseRecordService warehouseRecordService;

    @GetMapping("/pageOutboundRecordByKey")
    @ApiOperation(value = "分页查询出库记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageOutboundRecordByKey(@ApiParam(value = "分页查询出库记录请求实体", required = true) PageOutboundRecordByKeyQuery pageOutboundRecordByKeyQuery,
                                          @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outboundRecordService.pageOutboundRecordByKey(pageOutboundRecordByKeyQuery);
    }

    @GetMapping("/pageSearchElderByKey")
    @ApiOperation(value = "分页搜索老人", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageSearchElderByKey(@ApiParam(value = "分页搜索老人请求实体", required = true) PageSearchElderByKeyQuery pageSearchElderByKeyQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return depositRechargeService.pageSearchElderByKey(pageSearchElderByKeyQuery);
    }

    @GetMapping("/pageSearchStaffByKey")
    @ApiOperation(value = "分页搜索员工", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageSearchStaffByKey(@ApiParam(value = "分页搜索员工请求实体", required = true) PageSearchStaffByKeyQuery pageSearchStaffByKeyQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outboundRecordService.pageSearchStaffByKey(pageSearchStaffByKeyQuery);
    }

    @GetMapping("/listWarehouse")
    @ApiOperation(value = "获取仓库列表", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listWarehouse(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseRecordService.listWarehouse();
    }

    @GetMapping("/listWarehouseStaff")
    @ApiOperation(value = "获取登记人", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listWarehouseStaff(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseService.listWarehouseStaff();
    }

    @GetMapping("/pageWarehouseMaterialByKey")
    @ApiOperation(value = "分页查询仓库物资", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageWarehouseMaterialByKey(@ApiParam(value = "分页查询仓库物资请求实体", required = true) PageWarehouseMaterialByKeyQuery pageWarehouseMaterialByKeyQuery,
                                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outboundRecordService.pageWarehouseMaterialByKey(pageWarehouseMaterialByKeyQuery);
    }

    @PostMapping("/addOutboundRecord")
    @ApiOperation(value = "新增出库记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addOutboundRecord(@ApiParam(value = "新增出库记录请求实体", required = true) @RequestBody AddOutboundRecordQuery addOutboundRecordQuery,
                                    @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outboundRecordService.addOutboundRecord(addOutboundRecordQuery);
    }

    @GetMapping("/getOutboundRecordById")
    @ApiOperation(value = "根据编号查询出库记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getOutboundRecordById(@ApiParam(value = "根据编号查询出库记录请求参数", required = true) @RequestParam Long outboundRecordId,
                                        @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outboundRecordService.getOutboundRecordById(outboundRecordId);
    }

    @PutMapping("/auditOutboundRecord")
    @ApiOperation(value = "审核出库记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result auditOutboundRecord(@ApiParam(value = "审核出库记录请求实体", required = true) @RequestBody AuditOutboundRecordQuery auditOutboundRecordQuery,
                                      @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outboundRecordService.auditOutboundRecord(auditOutboundRecordQuery);
    }

    @DeleteMapping("/deleteOutboundRecord")
    @ApiOperation(value = "删除出库记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteOutboundRecord(@ApiParam(value = "删除出库记录请求参数", required = true) @RequestParam Long outboundRecordId,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return outboundRecordService.deleteOutboundRecord(outboundRecordId);
    }
}
