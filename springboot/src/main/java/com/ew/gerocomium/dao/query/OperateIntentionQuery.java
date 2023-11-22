package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "操作意向客户请求实体")
public class OperateIntentionQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "姓名", required = true, example = "张三")
    private String name;
    @ApiModelProperty(value = "身份证号", required = true, example = "null")
    private String idNum;
    @ApiModelProperty(value = "年龄", required = true, example = "67")
    private Integer age;
    @ApiModelProperty(value = "性别", required = true, example = "男")
    private String sex;
    @ApiModelProperty(value = "电话", required = true, example = "13546574657")
    private String phone;
    @ApiModelProperty(value = "地址", required = true, example = "四川南充")
    private String address;
}
