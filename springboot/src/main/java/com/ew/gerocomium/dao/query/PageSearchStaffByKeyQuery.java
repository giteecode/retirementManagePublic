package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页搜索老人请求实体")
public class PageSearchStaffByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "员工姓名", required = false, example = "李四")
    private String name;
    @ApiModelProperty(value = "员工性别", required = false, example = "男/女")
    private String sex;
}
