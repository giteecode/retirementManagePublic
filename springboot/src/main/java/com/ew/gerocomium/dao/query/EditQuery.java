package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "修改密码请求实体")
public class EditQuery {
    @ApiModelProperty(value = "旧密码", required = true, example = "123456")
    private String oldPass;
    @ApiModelProperty(value = "新密码", required = true, example = "123456")
    private String newPass;
}
