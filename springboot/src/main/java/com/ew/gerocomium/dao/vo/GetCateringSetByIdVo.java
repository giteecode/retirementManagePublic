package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "根据编号查询套餐信息响应实体")
public class GetCateringSetByIdVo {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "套餐名称", example = "颐养套餐")
    private String name;
    @ApiModelProperty(value = "月套餐费用", example = "1200")
    private BigDecimal monthPrice;
    @ApiModelProperty(value = "护理等级服务列表", example = "{}")
    private List<SetDishesVo> setDishesVoList;

    @Data
    @ApiModel(value = "参加活动老人响应实体")
    public static class SetDishesVo {
        @ApiModelProperty(value = "id", example = "1")
        private Long id;
        @ApiModelProperty(value = "菜品名称", example = "粥")
        private String name;
        @ApiModelProperty(value = "菜品价格", example = "2")
        private BigDecimal price;
    }
}
