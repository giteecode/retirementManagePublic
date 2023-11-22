package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "新增点餐请求实体")
public class AddOrderQuery {
    @ApiModelProperty(value = "老人编号", required = true, example = "1")
    private Long elderId;
    @ApiModelProperty(value = "就餐时间", required = true, example = "2022-12-14")
    private String dineDate;
    @ApiModelProperty(value = "就餐方式", required = true, example = "送餐/堂食")
    private String dineType;
    @ApiModelProperty(value = "就餐方式", required = true, example = "[{'dishesId':1,'orderNum':2}]")
    private List<AddOrderDishesQuery> orderDishesList;

    @Data
    @ApiModel(value = "新增点餐食物请求实体")
    public static class AddOrderDishesQuery {
        @ApiModelProperty(value = "菜品编号", required = true, example = "1")
        private Long dishesId;
        @ApiModelProperty(value = "菜品份数", required = true, example = "2")
        private Integer orderNum;
    }
}
