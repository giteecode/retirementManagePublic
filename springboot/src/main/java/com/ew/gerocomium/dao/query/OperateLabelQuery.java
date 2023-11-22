package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "操作客户标签请求实体")
public class OperateLabelQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "typeId", required = false, example = "1")
    private Long typeId;
    @ApiModelProperty(value = "名称", required = true, example = "看书")
    private String name;
    @ApiModelProperty(value = "颜色", required = true, example = "rgb")
    private String color;
}
