package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "根据预定编号和老人编号获取预定信息请求实体")
public class GetReserveByReserveIdAndElderIdQuery {
    @ApiModelProperty(value = "预定编号", required = true, example = "1")
    private Long reserveId;
    @ApiModelProperty(value = "老人编号", required = true, example = "1")
    private Long elderId;
}
