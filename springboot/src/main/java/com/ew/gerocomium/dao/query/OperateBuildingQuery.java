package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "操作楼栋请求实体")
public class OperateBuildingQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "楼栋名称", required = true, example = "爱心楼")
    private String name;
    @ApiModelProperty(value = "楼层数量", required = true, example = "5")
    private Integer floorNum;
}
