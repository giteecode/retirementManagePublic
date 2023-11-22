package com.ew.gerocomium.dao.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "客户标签响应实体")
public class GetElderLabelByIdLabelVo {
    @ApiModelProperty(value = "编号", example = "1")
    @JsonIgnore
    private Long id;
    @ApiModelProperty(value = "名称", example = "看书")
    private String name;
    @ApiModelProperty(value = "颜色", example = "rgb")
    private String color;
}
