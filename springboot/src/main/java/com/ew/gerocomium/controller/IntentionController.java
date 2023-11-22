package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.service.IntentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "意向客户")
@RestController
@RequestMapping("/intention")
@PreAuthorize("@AuthorityAssert.hasAuthority('/soles/intention')")
public class IntentionController {
    @Resource
    private IntentionService intentionService;

    @GetMapping("/listLabel")
    @ApiOperation(value = "客户标签", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listLabel(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.listLabel();
    }

    @GetMapping("/pageIntentionByKey")
    @ApiOperation(value = "分页查询意向客户", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageIntentionByKey(@ApiParam(value = "分页查询意向客户请求实体", required = true) PageIntentionByKeyQuery pageIntentionByKeyQuery,
                                     @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.pageIntentionByKey(pageIntentionByKeyQuery);
    }

    @GetMapping("/pageSearchElderByKey")
    @ApiOperation(value = "分页搜索老人", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageSearchElderByKey(@ApiParam(value = "分页搜索老人请求实体", required = true) PageSearchElderByKeyQuery pageSearchElderByKeyQuery,
                                        @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.pageSearchElderByKey(pageSearchElderByKeyQuery);
    }

    @PostMapping("/addIntention")
    @ApiOperation(value = "新增意向客户", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addIntention(@ApiParam(value = "新增意向客户请求实体", required = true) @RequestBody OperateIntentionQuery operateIntentionQuery,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.addIntention(operateIntentionQuery);
    }

    @GetMapping("/getIntentById")
    @ApiOperation(value = "根据编号获取意向客户", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getIntentById(@ApiParam(value = "根据编号获取意向客户请求参数", required = true) @RequestParam Long elderId,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.getIntentById(elderId);
    }

    @GetMapping("/getElderLabelById")
    @ApiOperation(value = "根据编号获取意向客户标签", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getElderLabelById(@ApiParam(value = "根据编号获取意向客户标签请求参数", required = true) @RequestParam Long elderId,
                                    @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.getElderLabelById(elderId);
    }

    @PutMapping("/editIntention")
    @ApiOperation(value = "编辑意向客户", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editIntention(@ApiParam(value = "编辑意向客户请求实体", required = true) @RequestBody OperateIntentionQuery operateIntentionQuery,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.editIntention(operateIntentionQuery);
    }

    @GetMapping("/getEditElderLabelById")
    @ApiOperation(value = "根据编号获取编辑意向客户标签", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getEditElderLabelById(@ApiParam(value = "根据编号获取编辑意向客户标签请求参数", required = true) @RequestParam Long elderId,
                                        @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.getEditElderLabelById(elderId);
    }

    @PutMapping("/editElderLabel")
    @ApiOperation(value = "编辑老人标签", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editElderLabel(@ApiParam(value = "编辑老人标签请求实体", required = true) @RequestBody EditElderLabelQuery editElderLabelQuery,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.editElderLabel(editElderLabelQuery);
    }

    @GetMapping("/pageVisitPlan")
    @ApiOperation(value = "分页查询回访计划", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageVisitPlan(@ApiParam(value = "分页查询回访计划请求实体", required = true) PageVisitPlanQuery pageVisitPlanQuery,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.pageVisitPlan(pageVisitPlanQuery);
    }

    @PostMapping("/addVisitPlan")
    @ApiOperation(value = "新增回访计划", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addVisitPlan(@ApiParam(value = "新增回访计划请求实体", required = true) @RequestBody AddVisitPlanQuery addVisitPlanQuery,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.addVisitPlan(addVisitPlanQuery);
    }

    @PutMapping("/executeVisitPlan")
    @ApiOperation(value = "执行回访计划", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result executeVisitPlan(@ApiParam(value = "执行回访计划请求实体", required = true) @RequestBody CompleteVisitPlanQuery completeVisitPlanQuery,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.executeVisitPlan(completeVisitPlanQuery);
    }

    @DeleteMapping("/deleteVisitPlan")
    @ApiOperation(value = "删除回访计划", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteVisitPlan(@ApiParam(value = "删除回访计划请求参数", required = true) @RequestParam Long visitPlanId,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.deleteVisitPlan(visitPlanId);
    }

    @GetMapping("/pageCommunicationRecord")
    @ApiOperation(value = "分页查询沟通记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageCommunicationRecord(@ApiParam(value = "分页查询沟通记录请求实体", required = true) PageCommunicationRecordQuery pageCommunicationRecordQuery,
                                          @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.pageCommunicationRecord(pageCommunicationRecordQuery);
    }

    @PostMapping("/addCommunicationRecord")
    @ApiOperation(value = "新增沟通记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addCommunicationRecord(@ApiParam(value = "新增沟通记录请求实体", required = true) @RequestBody AddCommunicationRecordQuery addCommunicationRecordQuery,
                                         @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.addCommunicationRecord(addCommunicationRecordQuery);
    }

    @PutMapping("/editCommunicationRecord")
    @ApiOperation(value = "编辑沟通记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editCommunicationRecord(@ApiParam(value = "编辑沟通记录请求实体", required = true) @RequestBody EditCommunicationRecordQuery editCommunicationRecordQuery,
                                          @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.editCommunicationRecord(editCommunicationRecordQuery);
    }

    @DeleteMapping("/deleteCommunicationRecord")
    @ApiOperation(value = "删除沟通记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteCommunicationRecord(@ApiParam(value = "删除沟通记录请求参数", required = true) @RequestParam Long communicationRecordId,
                                            @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.deleteCommunicationRecord(communicationRecordId);
    }
}
