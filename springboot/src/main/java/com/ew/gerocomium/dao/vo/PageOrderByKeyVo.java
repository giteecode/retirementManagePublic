package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询点餐响应实体")
public class PageOrderByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "老人姓名", example = "张三")
    private String elderName;
    @ApiModelProperty(value = "老人电话", example = "13546575678")
    private String elderPhone;
    @ApiModelProperty(value = "就餐时间", example = "2022-12-12")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dineDate;
    @ApiModelProperty(value = "就餐方式", example = "堂食")
    private String dineType;
    @ApiModelProperty(value = "送餐人员姓名", example = "张三")
    private String staffName;
    @ApiModelProperty(value = "送餐时间", example = "2022-12-12")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String deliverDishesDate;
    @ApiModelProperty(value = "支付总额", example = "200")
    private BigDecimal payAmount;
    @ApiModelProperty(value = "订单状态", example = "待支付/待送餐")
    private String orderFlag;
}
