package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.OperateIntentionQuery;
import com.ew.gerocomium.dao.query.PageElderByKeyQuery;
import com.ew.gerocomium.service.CheckContractService;
import com.ew.gerocomium.service.ElderRecordService;
import com.ew.gerocomium.service.IntentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@Api(tags = "长者档案")
@RestController
@RequestMapping("/elderRecord")
@PreAuthorize("@AuthorityAssert.hasAuthority('/people/old')")
public class ElderRecordController {
    @Resource
    private ElderRecordService elderRecordService;
    @Resource
    private IntentionService intentionService;
    @Resource
    private CheckContractService checkContractService;

    @GetMapping("/exportExcel")
    @ApiOperation(value = "导出excel", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result exportExcel(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) throws IOException {
        return elderRecordService.exportExcel();
    }

    @GetMapping("/pageElderByKey")
    @ApiOperation(value = "分页查询长者", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageElderByKey(@ApiParam(value = "分页查询长者请求实体", required = true) PageElderByKeyQuery pageElderByKeyQuery,
                                 @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return elderRecordService.pageElderByKey(pageElderByKeyQuery);
    }

    @GetMapping("/getElderRecordById")
    @ApiOperation(value = "根据编号获取长者档案", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getElderRecordById(@ApiParam(value = "根据编号获取长者档案请求参数", required = true) @RequestParam Long elderId,
                                     @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return elderRecordService.getElderRecordById(elderId);
    }

    @GetMapping("/getElderById")
    @ApiOperation(value = "根据编号获取长者信息", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getElderById(@ApiParam(value = "根据编号获取长者信息请求参数", required = true) @RequestParam Long elderId,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.getIntentById(elderId);
    }

    @PutMapping("/editElder")
    @ApiOperation(value = "编辑长者", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editElder(@ApiParam(value = "编辑长者请求实体", required = true) @RequestBody OperateIntentionQuery operateIntentionQuery,
                            @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return intentionService.editIntention(operateIntentionQuery);
    }

    @DeleteMapping("/deleteElder")
    @ApiOperation(value = "删除长者", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteCheckContract(@ApiParam(value = "删除长者请求参数", required = true) @RequestParam Long elderId,
                                      @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return checkContractService.deleteCheckContract(elderId);
    }
}
