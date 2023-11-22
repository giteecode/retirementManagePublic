package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "编辑事故登记请求实体")
public class EditAccidentQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "护工编号", required = true, example = "1")
    private Long staffId;
    @ApiModelProperty(value = "发生时间", required = true, example = "2022-12-13")
    private String occurDate;
    @ApiModelProperty(value = "事故描述", required = true, example = "摔倒")
    private String description;
    @ApiModelProperty(value = "事故图片", required = true, example = "url")
    private String picture;
}
