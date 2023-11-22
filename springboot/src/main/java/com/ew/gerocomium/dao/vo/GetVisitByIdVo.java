package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "根据编号获取来访登记响应实体")
public class GetVisitByIdVo {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "老人姓名", example = "张三")
    private String elderName;
    @ApiModelProperty(value = "来访者姓名", example = "李四")
    private String visitName;
    @ApiModelProperty(value = "来访者电话", example = "13647563647")
    private String visitPhone;
    @ApiModelProperty(value = "与老人关系", example = "父子")
    private String relation;
    @ApiModelProperty(value = "来访时间", example = "2022-12-13")
    private Date visitDate;
    @ApiModelProperty(value = "来访者人数", example = "3")
    private Long visitNum;
}
