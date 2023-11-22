package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "根据预定编号和老人编号获取预定信息响应实体")
public class GetReserveByReserveIdAndElderIdVo {
    @ApiModelProperty(value = "老人姓名", example = "张三")
    private String elderName;
    @ApiModelProperty(value = "身份证号", example = "null")
    private String idNum;
    @ApiModelProperty(value = "老人性别", example = "男")
    private String elderSex;
    @ApiModelProperty(value = "老人年龄", example = "67")
    private Integer elderAge;
    @ApiModelProperty(value = "老人联系电话", example = "13546575678")
    private String elderPhone;
    @ApiModelProperty(value = "老人地址", example = "13546575678")
    private String elderAddress;
    @ApiModelProperty(value = "预定床位名称", example = "爱心楼-1层-1房-1床")
    private String bedName;
    @ApiModelProperty(value = "销售人员编号", example = "1")
    private Long staffId;
    @ApiModelProperty(value = "交款人姓名", example = "张三")
    private String payerName;
    @ApiModelProperty(value = "交款人联系电话", example = "13546575678")
    private String payerPhone;
    @ApiModelProperty(value = "预定到期时间", example = "2022-12-13")
    private String dueDate;
    @ApiModelProperty(value = "定金", example = "1000")
    private BigDecimal deposit;
}
