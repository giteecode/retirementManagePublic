package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "审核老人费用详情请求实体")
public class AuditElderFeeQuery {
    @ApiModelProperty(value = "申请编号", required = true, example = "1")
    private Long applyId;
    @ApiModelProperty(value = "老人编号", required = true, example = "1")
    private Long elderId;
    @ApiModelProperty(value = "审核结果", required = true, example = "通过/不通过")
    private String auditResult;
}
