package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "陪同人响应实体")
public class AccompanyingPersonVo {
    @ApiModelProperty(value = "姓名", example = "1")
    private String name;
    @ApiModelProperty(value = "电话", example = "1")
    private String phone;
}
