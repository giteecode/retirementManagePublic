package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "审核入库记录请求实体")
public class AuditWarehouseRecordQuery {
    @ApiModelProperty(value = "入库记录编号", required = true, example = "1")
    private Long warehouseRecordId;
    @ApiModelProperty(value = "审核结果", required = true, example = "通过/不通过")
    private String auditResult;
}
