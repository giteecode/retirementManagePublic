package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "删除节点请求实体")
public class DeleteNodeQuery {
    @ApiModelProperty(value = "id", required = true, example = "1")
    private Long id;
    @ApiModelProperty(value = "节点标识", required = true, example = "楼栋/楼层/房间")
    private String mark;
}
