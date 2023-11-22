package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.AuditElderFeeQuery;
import com.ew.gerocomium.dao.query.PageRetreatAuditQuery;
import com.ew.gerocomium.service.RetreatAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "退住费用审核")
@RestController
@RequestMapping("/retreatAudit")
@PreAuthorize("@AuthorityAssert.hasAuthority('/fee/audit')")
public class RetreatAuditController {
    @Resource
    private RetreatAuditService retreatAuditService;

    @GetMapping("/pageRetreatAuditByKey")
    @ApiOperation(value = "分页查询退住审核", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageRetreatAuditByKey(@ApiParam(value = "分页查询退住审核请求实体", required = true) PageRetreatAuditQuery pageRetreatAuditQuery,
                                        @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return retreatAuditService.pageRetreatAuditByKey(pageRetreatAuditQuery);
    }

    @GetMapping("/getElderFeeById")
    @ApiOperation(value = "根据编号获取老人费用详情", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getElderFeeById(@ApiParam(value = "根据获取老人费用详情请求参数", required = true) @RequestParam Long elderId,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return retreatAuditService.getElderFeeById(elderId);
    }

    @PutMapping("/auditElderFee")
    @ApiOperation(value = "审核老人费用详情", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result auditElderFee(@ApiParam(value = "审核老人费用详情请求实体", required = true) @RequestBody AuditElderFeeQuery auditElderFeeQuery,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return retreatAuditService.auditElderFee(auditElderFeeQuery);
    }
}
