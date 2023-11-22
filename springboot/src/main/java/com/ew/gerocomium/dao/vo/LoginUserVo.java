package com.ew.gerocomium.dao.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "登录用户响应实体")
public class LoginUserVo {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;
    @ApiModelProperty(value = "头像", example = "url")
    private String avator;
    @ApiModelProperty(value = "电话", example = "13546464400")
    @JsonIgnore
    private String phone;
    @ApiModelProperty(value = "密码", example = "jkhg13498569sdfkjg")
    @JsonIgnore
    private String pass;
    @ApiModelProperty(value = "权限id列表", example = "{}")
    private List<Long> authIdList;
    @ApiModelProperty(value = "权限url列表", example = "{}")
    @JsonIgnore
    private List<String> authUrlList;
    @ApiModelProperty(value = "token", example = "null")
    private String token;
}
