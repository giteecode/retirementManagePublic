package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询餐饮套餐响应实体")
public class PageCateringSetByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "套餐名称", example = "颐养食谱")
    private String name;
    @ApiModelProperty(value = "月套餐费用", example = "1200")
    private BigDecimal monthPrice;
}
