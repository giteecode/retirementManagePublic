package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "客户来源渠道请求实体")
public class ClientSourceQuery {
    @ApiModelProperty(value = "开始时间", required = false, example = "2022-10-10")
    private String startTime;
    @ApiModelProperty(value = "结束时间", required = false, example = "2022-10-11")
    private String endTime;
}
