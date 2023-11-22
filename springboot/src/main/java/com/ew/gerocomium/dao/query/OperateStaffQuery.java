package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "操作员工请求实体")
public class OperateStaffQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "角色编号", required = true, example = "1")
    private Long roleId;
    @ApiModelProperty(value = "员工姓名", required = true, example = "张三")
    private String name;
    @ApiModelProperty(value = "员工身份证号", required = true, example = "null")
    private String idNum;
    @ApiModelProperty(value = "员工年龄", required = true, example = "34")
    private Integer age;
    @ApiModelProperty(value = "员工性别", required = true, example = "男")
    private String sex;
    @ApiModelProperty(value = "员工电话", required = true, example = "13546574657")
    private String phone;
    @ApiModelProperty(value = "员工邮箱", required = true, example = "93485639@qq.com")
    private String email;
    @ApiModelProperty(value = "员工地址", required = true, example = "四川南充")
    private String address;
    @ApiModelProperty(value = "员工头像", required = true, example = "url")
    private String avator;
}
