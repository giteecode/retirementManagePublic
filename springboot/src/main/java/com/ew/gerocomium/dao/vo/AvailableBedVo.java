package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "可售床位响应实体")
public class AvailableBedVo {
    @ApiModelProperty(value = "空闲房间", example = "1")
    private Long idleRoomNum;
    @ApiModelProperty(value = "空闲床位", example = "1")
    private Long idleBedNum;
    @ApiModelProperty(value = "已登记退床", example = "1")
    private Long exitAuditNum;
}
