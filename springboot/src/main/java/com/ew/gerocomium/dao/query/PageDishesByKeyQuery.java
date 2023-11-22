package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询菜品请求实体")
public class PageDishesByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "菜品分类编号", required = false, example = "1")
    private Long typeId;
    @ApiModelProperty(value = "菜品名称", required = false, example = "粥")
    private String dishesName;
}
