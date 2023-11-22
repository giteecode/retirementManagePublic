package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "根据编号查询点餐响应实体")
public class GetOrderByIdVo {
    @ApiModelProperty(value = "老人姓名", example = "张三")
    private String elderName;
    @ApiModelProperty(value = "老人电话", example = "13546575678")
    private String elderPhone;
    @ApiModelProperty(value = "就餐时间", example = "2022-12-12")
    private String dineDate;
    @ApiModelProperty(value = "就餐方式", example = "堂食")
    private String dineType;
    @ApiModelProperty(value = "送餐人员姓名", example = "张三")
    private String staffName;
    @ApiModelProperty(value = "送餐时间", example = "2022-12-12")
    private String deliverDishesDate;
    @ApiModelProperty(value = "订单菜品列表", example = "{}")
    private List<OrderDishesVo> orderDishesVoList;

    @EqualsAndHashCode(callSuper = true)
    @Data
    @ApiModel(value = "菜单菜品响应实体")
    public static class OrderDishesVo extends Rank {
        @ApiModelProperty(value = "菜品名称", example = "粥")
        private String dishesName;
        @ApiModelProperty(value = "菜品价格", example = "1")
        private BigDecimal dishesPrice;
        @ApiModelProperty(value = "菜品份数", example = "2")
        private Integer orderNum;
        @ApiModelProperty(value = "套餐标记", example = "Y")
        private String setFlag;
        @ApiModelProperty(value = "菜品总额", example = "2")
        private BigDecimal totalAmount;
        @ApiModelProperty(value = "实际总额", example = "1")
        private BigDecimal reallyAmount;
    }
}
