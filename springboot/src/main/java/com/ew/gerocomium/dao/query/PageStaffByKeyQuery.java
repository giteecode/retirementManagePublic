package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询员工请求实体")
public class PageStaffByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "角色编号", required = false, example = "1")
    private Long roleId;
    @ApiModelProperty(value = "员工姓名", required = false, example = "张三")
    private String name;
    @ApiModelProperty(value = "员工电话", required = false, example = "13547564867")
    private String phone;
}
