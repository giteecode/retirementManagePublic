package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.ClientSourceQuery;
import com.ew.gerocomium.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "首页")
@RestController
@RequestMapping("/home")
public class HomeController {
    @Resource
    private HomeService homeService;

    @GetMapping("/todayOverview")
    @ApiOperation(value = "今日概览", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result todayOverview(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return homeService.todayOverview();
    }

    @GetMapping("/availableBed")
    @ApiOperation(value = "可售床位", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result availableBed(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return homeService.availableBed();
    }

    @GetMapping("/todaySaleFollow")
    @ApiOperation(value = "今日销售跟进", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result todaySaleFollow(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return homeService.todaySaleFollow();
    }

    @GetMapping("/monthPerformanceRank")
    @ApiOperation(value = "本月业绩排行", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result monthPerformanceRank(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return homeService.monthPerformanceRank();
    }

    @GetMapping("/clientSource")
    @ApiOperation(value = "客户来源渠道", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result clientSource(@ApiParam(value = "接口访问请求头", required = true) ClientSourceQuery clientSourceQuery,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return homeService.clientSource(clientSourceQuery);
    }

    @GetMapping("/businessTrend")
    @ApiOperation(value = "业务趋势", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result businessTrend(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return homeService.businessTrend();
    }
}
