package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "发送验证码请求实体")
public class SendCodeQuery {
    @ApiModelProperty(value = "账号", required = true, example = "13545676756")
    private String account;
    @ApiModelProperty(value = "密码", required = true, example = "123456")
    private String pass;
}
