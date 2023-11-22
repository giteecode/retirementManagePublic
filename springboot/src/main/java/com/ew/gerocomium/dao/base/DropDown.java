package com.ew.gerocomium.dao.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "下拉列表响应实体")
public class DropDown {
    @ApiModelProperty(value = "编号", example = "1")
    private Long id;
    @ApiModelProperty(value = "名称", example = "null")
    private String name;
}
