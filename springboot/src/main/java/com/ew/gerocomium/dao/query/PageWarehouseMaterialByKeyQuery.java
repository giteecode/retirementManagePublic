package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询仓库物资请求实体")
public class PageWarehouseMaterialByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "仓库编号", required = true, example = "1")
    private Long warehouseId;
    @ApiModelProperty(value = "物资名称", required = false, example = "当归")
    private String materialName;
}
