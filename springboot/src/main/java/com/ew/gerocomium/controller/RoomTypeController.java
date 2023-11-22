package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.OperateRoomTypeQuery;
import com.ew.gerocomium.dao.query.PageRoomTypeByKeyQuery;
import com.ew.gerocomium.service.RoomTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "房间类型")
@RestController
@RequestMapping("/roomType")
@PreAuthorize("@AuthorityAssert.hasAuthority('/base/check-in/room')")
public class RoomTypeController {
    @Resource
    private RoomTypeService roomTypeService;

    @GetMapping("/pageRoomTypeByKey")
    @ApiOperation(value = "分页查询房间类型", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageRoomTypeByKey(@ApiParam(value = "分页查询房间类型请求实体", required = true) PageRoomTypeByKeyQuery pageRoomTypeByKeyQuery,
                                    @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return roomTypeService.pageRoomTypeByKey(pageRoomTypeByKeyQuery);
    }

    @PostMapping("/addRoomType")
    @ApiOperation(value = "新增房间类型", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addRoomType(@ApiParam(value = "新增房间类型请求实体", required = true) @RequestBody OperateRoomTypeQuery operateRoomTypeQuery,
                              @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return roomTypeService.addRoomType(operateRoomTypeQuery);
    }

    @GetMapping("/getRoomTypeById")
    @ApiOperation(value = "根据编号获取房间类型", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getRoomTypeById(@ApiParam(value = "根据编号获取房间类型请求参数", required = true) @RequestParam Long roomTypeId,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return roomTypeService.getRoomTypeById(roomTypeId);
    }

    @PutMapping("/editRoomType")
    @ApiOperation(value = "编辑房间类型", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editRoomType(@ApiParam(value = "编辑房间类型请求实体", required = true) @RequestBody OperateRoomTypeQuery operateRoomTypeQuery,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return roomTypeService.editRoomType(operateRoomTypeQuery);
    }

    @DeleteMapping("/deleteRoomType")
    @ApiOperation(value = "删除房间类型", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteRoomType(@ApiParam(value = "删除房间类型请求参数", required = true) @RequestParam Long roomTypeId,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return roomTypeService.deleteRoomType(roomTypeId);
    }
}
