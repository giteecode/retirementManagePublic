package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "操作房间类型请求实体")
public class OperateRoomTypeQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "房间类型名称", required = true, example = "单人间")
    private String name;
    @ApiModelProperty(value = "每月价格", required = true, example = "500")
    private BigDecimal monthPrice;
}
