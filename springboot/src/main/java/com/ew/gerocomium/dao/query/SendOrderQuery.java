package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "送餐请求实体")
public class SendOrderQuery {
    @ApiModelProperty(value = "id", required = true, example = "1")
    private Long id;
    @ApiModelProperty(value = "送餐人员编号", required = true, example = "1")
    private Long staffId;
    @ApiModelProperty(value = "送餐时间", required = true, example = "2022-12-13")
    private String deliverDishesDate;
}
