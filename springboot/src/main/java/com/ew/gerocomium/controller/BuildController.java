package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.service.BuildService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "楼栋管理")
@RestController
@RequestMapping("/build")
@PreAuthorize("@AuthorityAssert.hasAuthority('/base/check-in/building')")
public class BuildController {
    @Resource
    private BuildService buildService;

    @GetMapping("/getNoBedTree")
    @ApiOperation(value = "获取楼栋-楼层-房间树", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getNoBedTreeAndPageBedByKey(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.getNoBedTreeAndPageBedByKey();
    }

    @GetMapping("/pageBedByKey")
    @ApiOperation(value = "分页查询床位", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageBedByKey(@ApiParam(value = "分页查询床位请求实体", required = true) PageBedByKeyQuery pageBedByKeyQuery,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.pageBedByKey(pageBedByKeyQuery);
    }

    @PostMapping("/addBuilding")
    @ApiOperation(value = "新增楼栋", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addBuilding(@ApiParam(value = "新增楼栋请求实体", required = true) @RequestBody OperateBuildingQuery operateBuildingQuery,
                              @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.addBuilding(operateBuildingQuery);
    }

    @GetMapping("/getBuildingById")
    @ApiOperation(value = "根据编号获取楼栋", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getBuildingById(@ApiParam(value = "根据编号获取楼栋请求参数", required = true) @RequestParam Long buildingId,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.getBuildingById(buildingId);
    }

    @PutMapping("/editBuilding")
    @ApiOperation(value = "编辑楼栋", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editBuilding(@ApiParam(value = "编辑楼栋请求实体", required = true) @RequestBody OperateBuildingQuery operateBuildingQuery,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.editBuilding(operateBuildingQuery);
    }

    @PostMapping("/addFloor")
    @ApiOperation(value = "新增楼层", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addFloor(@ApiParam(value = "新增楼层请求实体", required = true) @RequestBody OperateFloorQuery operateFloorQuery,
                           @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.addFloor(operateFloorQuery);
    }

    @GetMapping("/getFloorById")
    @ApiOperation(value = "根据编号获取楼层", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getFloorById(@ApiParam(value = "根据编号获取楼层请求参数", required = true) @RequestParam Long floorId,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.getFloorById(floorId);
    }

    @PutMapping("/editFloor")
    @ApiOperation(value = "编辑楼层", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editFloor(@ApiParam(value = "编辑楼层请求实体", required = true) @RequestBody OperateFloorQuery operateFloorQuery,
                            @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.editFloor(operateFloorQuery);
    }

    @GetMapping("/listRoomType")
    @ApiOperation(value = "获取房间类型列表", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listRoomType(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.listRoomType();
    }

    @PostMapping("/addRoom")
    @ApiOperation(value = "新增房间", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addRoom(@ApiParam(value = "新增房间请求实体", required = true) @RequestBody OperateRoomQuery operateRoomQuery,
                          @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.addRoom(operateRoomQuery);
    }

    @GetMapping("/getRoomById")
    @ApiOperation(value = "根据编号获取房间", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getRoomById(@ApiParam(value = "根据编号获取房间请求参数", required = true) @RequestParam Long roomId,
                              @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.getRoomById(roomId);
    }

    @PutMapping("/editRoom")
    @ApiOperation(value = "编辑房间", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editRoom(@ApiParam(value = "编辑房间请求实体", required = true) @RequestBody OperateRoomQuery operateRoomQuery,
                           @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.editRoom(operateRoomQuery);
    }

    @DeleteMapping("/deleteNode")
    @ApiOperation(value = "删除节点", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteNode(@ApiParam(value = "删除节点请求实体", required = true) DeleteNodeQuery deleteNodeQuery,
                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.deleteNode(deleteNodeQuery);
    }

    @PostMapping("/addBed")
    @ApiOperation(value = "新增床位", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addBed(@ApiParam(value = "新增床位请求实体", required = true) @RequestBody OperateBedQuery operateBedQuery,
                         @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.addBed(operateBedQuery);
    }

    @GetMapping("/getBedById")
    @ApiOperation(value = "根据编号获取床位", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getBedById(@ApiParam(value = "根据编号获取床位请求参数", required = true) @RequestParam Long bedId,
                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.getBedById(bedId);
    }

    @PutMapping("/editBed")
    @ApiOperation(value = "编辑床位", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editBed(@ApiParam(value = "编辑床位请求实体", required = true) @RequestBody OperateBedQuery operateBedQuery,
                          @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.editBed(operateBedQuery);
    }

    @DeleteMapping("/deleteBed")
    @ApiOperation(value = "删除床位", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteBed(@ApiParam(value = "删除床位请求参数", required = true) @RequestParam Long bedId,
                            @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return buildService.deleteBed(bedId);
    }
}
