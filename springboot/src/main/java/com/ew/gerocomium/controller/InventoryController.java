package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.PageInventoryByKeyQuery;
import com.ew.gerocomium.service.InventoryService;
import com.ew.gerocomium.service.WarehouseRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "库存查询")
@RestController
@RequestMapping("/inventory")
@PreAuthorize("@AuthorityAssert.hasAuthority('/resource/search')")
public class InventoryController {
    @Resource
    private InventoryService inventoryService;
    @Resource
    private WarehouseRecordService warehouseRecordService;

    @GetMapping("/listWarehouse")
    @ApiOperation(value = "获取仓库列表", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listWarehouse(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseRecordService.listWarehouse();
    }

    @GetMapping("/pageInventoryByKey")
    @ApiOperation(value = "分页查询库存", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageInventoryByKey(@ApiParam(value = "分页查询库存请求实体", required = true) PageInventoryByKeyQuery pageInventoryByKeyQuery,
                                     @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return inventoryService.pageInventoryByKey(pageInventoryByKeyQuery);
    }
}
