package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "客户来源渠道响应实体")
public class ClientSourceVo {
    @ApiModelProperty(value = "来源渠道名称", example = "广告")
    private String sourceName;
    @ApiModelProperty(value = "咨询人数", example = "1")
    private Long consultNum;
}
