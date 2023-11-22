package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "今日概览响应实体")
public class TodayOverviewVo {
    @ApiModelProperty(value = "今日新增咨询", example = "1")
    private Long todayAddConsultNum;
    @ApiModelProperty(value = "今日新增预定", example = "1")
    private Long todayAddReserveNum;
    @ApiModelProperty(value = "今日新增合同", example = "1")
    private Long todayAddContractNum;
    @ApiModelProperty(value = "合同到期提醒", example = "1")
    private Long todayContractExpireNum;
}
