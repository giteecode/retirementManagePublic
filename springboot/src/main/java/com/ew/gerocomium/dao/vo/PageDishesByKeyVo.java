package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询意向客户响应实体")
public class PageDishesByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "类别名称", example = "早餐")
    private String typeName;
    @ApiModelProperty(value = "菜品名称", example = "粥")
    private String dishesName;
    @ApiModelProperty(value = "价格", example = "2")
    private BigDecimal price;
}
