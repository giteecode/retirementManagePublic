package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询事故登记请求实体")
public class PageAccidentByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "老人姓名", required = false, example = "张三")
    private String elderName;
    @ApiModelProperty(value = "护工姓名", required = false, example = "李四")
    private String staffName;
}
