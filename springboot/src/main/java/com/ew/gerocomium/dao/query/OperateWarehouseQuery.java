package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "新增仓库请求实体")
public class OperateWarehouseQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "仓库管理员编号", required = true, example = "1")
    private Long staffId;
    @ApiModelProperty(value = "仓库名称", required = true, example = "仓库")
    private String name;
}
