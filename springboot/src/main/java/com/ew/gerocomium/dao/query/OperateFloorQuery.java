package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "操作楼层请求实体")
public class OperateFloorQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "楼栋编号", required = true, example = "1")
    private Long buildingId;
    @ApiModelProperty(value = "楼层名称", required = true, example = "爱心楼")
    private String name;
    @ApiModelProperty(value = "房间数量", required = true, example = "20")
    private Integer roomNum;
    @ApiModelProperty(value = "楼栋楼层总数限制", required = true, example = "5")
    private Integer floorLimit;
}
