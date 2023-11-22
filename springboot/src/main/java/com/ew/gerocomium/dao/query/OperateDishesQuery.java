package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "操作菜品请求实体")
public class OperateDishesQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "菜品分类编号", required = true, example = "1")
    private Long typeId;
    @ApiModelProperty(value = "菜品名称", required = true, example = "粥")
    private String name;
    @ApiModelProperty(value = "菜品价格", required = true, example = "2")
    private BigDecimal price;
}
