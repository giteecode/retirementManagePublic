package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.ListRoomByKeyQuery;
import com.ew.gerocomium.service.BedPanoramaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "床位全景")
@RestController
@RequestMapping("/bedPanorama")
@PreAuthorize("@AuthorityAssert.hasAuthority('/check-in/bed')")
public class BedPanoramaController {
    @Resource
    private BedPanoramaService bedPanoramaService;

    @GetMapping("/listBuilding")
    @ApiOperation(value = "获取楼栋列表", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listBuilding(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return bedPanoramaService.listBuilding();
    }

    @GetMapping("/listFloorByBuildingId")
    @ApiOperation(value = "获取楼层列表", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listFloorByBuildingId(@ApiParam(value = "获取楼层列表请求参数", required = false) Long buildingId,
                                        @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return bedPanoramaService.listFloorByBuildingId(buildingId);
    }

    @GetMapping("/listRoomByKey")
    @ApiOperation(value = "获取房间列表", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listRoomByKey(@ApiParam(value = "获取房间列表请求实体", required = true) ListRoomByKeyQuery listRoomByKeyQuery,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return bedPanoramaService.listRoomByKey(listRoomByKeyQuery);
    }
}
