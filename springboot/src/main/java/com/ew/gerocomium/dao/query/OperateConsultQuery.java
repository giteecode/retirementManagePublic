package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "操作咨询请求实体")
public class OperateConsultQuery {
    @ApiModelProperty(value = "咨询人编号", required = false, example = "1")
    private Long consultId;
    @ApiModelProperty(value = "老人编号", required = false, example = "1")
    private Long elderId;
    @ApiModelProperty(value = "来源渠道编号", required = true, example = "1")
    private Long sourceId;
    @ApiModelProperty(value = "接待人编号", required = true, example = "1")
    private Long staffId;
    @ApiModelProperty(value = "咨询人姓名", required = true, example = "张三")
    private String consultName;
    @ApiModelProperty(value = "咨询人电话", required = true, example = "13546564657")
    private String consultPhone;
    @ApiModelProperty(value = "与老人关系", required = true, example = "父子")
    private String relation;
    @ApiModelProperty(value = "咨询日期", required = true, example = "2022-10-10")
    private String consultDate;
    @ApiModelProperty(value = "咨询内容", required = true, example = "无")
    private String consultContent;
    @ApiModelProperty(value = "老人姓名", required = true, example = "李四")
    private String elderName;
    @ApiModelProperty(value = "身份证号", required = true, example = "null")
    private String idNum;
    @ApiModelProperty(value = "年龄", required = true, example = "67")
    private String age;
    @ApiModelProperty(value = "性别(男/女)", required = true, example = "男")
    private String sex;
    @ApiModelProperty(value = "老人电话", required = true, example = "13546457878")
    private String elderPhone;
    @ApiModelProperty(value = "地址", required = true, example = "四川南充")
    private String address;
}
