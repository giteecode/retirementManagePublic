package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "新增预定请求实体")
public class AddReserveQuery {
    @ApiModelProperty(value = "床位编号", required = true, example = "1")
    private Long bedId;
    @ApiModelProperty(value = "营销人员编号", required = true, example = "1")
    private Long staffId;
    @ApiModelProperty(value = "交款人姓名", required = true, example = "张三")
    private String payerName;
    @ApiModelProperty(value = "交款人电话", required = true, example = "13546575678")
    private String payerPhone;
    @ApiModelProperty(value = "预定到期时间", required = true, example = "2022-12-13")
    private String dueDate;
    @ApiModelProperty(value = "预付定金", required = true, example = "1000")
    private BigDecimal deposit;
    @ApiModelProperty(value = "老人姓名", required = true, example = "张三")
    private String elderName;
    @ApiModelProperty(value = "老人身份证号", required = true, example = "null")
    private String idNum;
    @ApiModelProperty(value = "老人年龄", required = true, example = "78")
    private Integer elderAge;
    @ApiModelProperty(value = "老人性别", required = true, example = "男")
    private String elderSex;
    @ApiModelProperty(value = "老人电话", required = true, example = "13546574657")
    private String elderPhone;
    @ApiModelProperty(value = "老人地址", required = true, example = "四川南充")
    private String elderAddress;
}
