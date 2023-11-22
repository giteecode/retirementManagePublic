package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "预定过期响应实体")
public class ExpireReserveVo {
    @ApiModelProperty(value = "预定编号", example = "1")
    private Long reserveId;
    @ApiModelProperty(value = "老人编号", example = "1")
    private Long elderId;
    @ApiModelProperty(value = "老人状态", example = "预定")
    private String checkFlag;
    @ApiModelProperty(value = "床位编号", example = "1")
    private Long bedId;
    @ApiModelProperty(value = "床位状态", example = "预定")
    private String bedFlag;
}
