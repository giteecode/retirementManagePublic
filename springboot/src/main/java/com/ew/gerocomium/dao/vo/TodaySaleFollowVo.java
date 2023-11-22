package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "今日销售跟进响应实体")
public class TodaySaleFollowVo {
    @ApiModelProperty(value = "今日应回访", example = "1")
    private Long todayReturnVisitNum;
    @ApiModelProperty(value = "今日已回访", example = "1")
    private Long todayReturnedVisitNum;
    @ApiModelProperty(value = "待回访", example = "1")
    private Long stayReturnedVisitNum;
}
