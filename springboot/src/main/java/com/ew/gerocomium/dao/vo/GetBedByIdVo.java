package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "根据编号获取床位响应实体")
public class GetBedByIdVo {
    @ApiModelProperty(value = "床位编号", example = "1")
    private Long bedId;
    @ApiModelProperty(value = "床位名称", example = "爱心楼-1层-1房-1床")
    private String bedName;
    @ApiModelProperty(value = "房间类型", example = "单人间")
    private String roomType;
    @ApiModelProperty(value = "月房间费用", example = "1200")
    private BigDecimal monthPrice;
}
