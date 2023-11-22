package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "操作床位请求实体")
public class OperateBedQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "房间编号", required = true, example = "1")
    private Long roomId;
    @ApiModelProperty(value = "床位名称", required = true, example = "爱心楼")
    private String name;
    @ApiModelProperty(value = "房间床位总数限制", required = true, example = "2")
    private Integer bedLimit;
}
