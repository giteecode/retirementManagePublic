package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "操作客户标签请求实体")
public class OperateMaterialQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "typeId", required = true, example = "1")
    private Long typeId;
    @ApiModelProperty(value = "物资名称", required = true, example = "勺子")
    private String name;
    @ApiModelProperty(value = "物资单价", required = true, example = "10")
    private BigDecimal price;
}
