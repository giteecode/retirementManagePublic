package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "根据编号获取事故登记响应实体")
public class GetAccidentByIdVo {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "老人姓名", example = "张三")
    private String elderName;
    @ApiModelProperty(value = "护工编号", example = "1")
    private Long staffId;
    @ApiModelProperty(value = "发生时间", example = "2022-12-23")
    private Date occurDate;
    @ApiModelProperty(value = "事故描述", example = "摔倒")
    private String description;
    @ApiModelProperty(value = "事故图片", example = "url")
    private String picture;
}
