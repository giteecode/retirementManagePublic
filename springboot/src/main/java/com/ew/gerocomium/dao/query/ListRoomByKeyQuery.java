package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "获取房间列表请求实体")
public class ListRoomByKeyQuery {
    @ApiModelProperty(value = "楼栋编号", required = false, example = "1")
    private Long buildingId;
    @ApiModelProperty(value = "楼层编号", required = false, example = "1")
    private Long floorId;
    @ApiModelProperty(value = "老人姓名", required = false, example = "张三")
    private String elderName;
}
