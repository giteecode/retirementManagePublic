package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "操作服务请求实体")
public class OperateServiceQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "服务类型编号", required = true, example = "1")
    private Long typeId;
    @ApiModelProperty(value = "服务名称", required = true, example = "心电检测")
    private String name;
    @ApiModelProperty(value = "收费方式", required = true, example = "按次/按月")
    private String chargeMethod;
    @ApiModelProperty(value = "服务费用", required = true, example = "120")
    private BigDecimal price;
    @ApiModelProperty(value = "所需时间（分）", required = true, example = "30")
    private Integer needDate;
}
