package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "业务趋势响应实体")
public class BusinessTrendVo {
    @ApiModelProperty(value = "月份", example = "2023-01")
    private String month;
    @ApiModelProperty(value = "咨询人数", example = "1")
    private Long consultNum;
    @ApiModelProperty(value = "合同人数", example = "1")
    private Long contractNum;
}
