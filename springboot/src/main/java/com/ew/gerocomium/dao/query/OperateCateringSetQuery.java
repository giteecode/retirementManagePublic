package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "操作餐饮套餐请求实体")
public class OperateCateringSetQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "套餐名称", required = true, example = "颐养套餐")
    private String name;
    @ApiModelProperty(value = "月套餐费用", required = true, example = "1200")
    private BigDecimal monthPrice;
    @ApiModelProperty(value = "菜品编号列表", required = true, example = "{}")
    private List<Long> dishesIdList;
}
