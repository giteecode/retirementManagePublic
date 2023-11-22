package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "操作房间请求实体")
public class OperateRoomQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "房间类型编号", required = true, example = "1")
    private Long typeId;
    @ApiModelProperty(value = "楼层编号", required = true, example = "1")
    private Long floorId;
    @ApiModelProperty(value = "房间名称", required = true, example = "爱心楼")
    private String name;
    @ApiModelProperty(value = "床位数量", required = true, example = "2")
    private Integer bedNum;
    @ApiModelProperty(value = "楼层房间总数限制", required = true, example = "20")
    private Integer roomLimit;
}
