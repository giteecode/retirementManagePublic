package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登录请求实体")
public class LoginQuery {
    @ApiModelProperty(value = "电话",required = true, example = "13545676756")
    private String phone;
    @ApiModelProperty(value = "密码",required = true, example = "123456")
    private String pass;
}
