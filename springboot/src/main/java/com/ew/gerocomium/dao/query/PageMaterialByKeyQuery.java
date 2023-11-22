package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询物资请求实体")
public class PageMaterialByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "物资分类编号", required = false, example = "1")
    private Long materialTypeId;
    @ApiModelProperty(value = "物资名称", required = false, example = "勺子")
    private String materialName;
}
