package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询预定响应实体")
public class PageReserveByKeyVo extends Rank {
    @ApiModelProperty(value = "预定编号", example = "1")
    private Long reserveId;
    @ApiModelProperty(value = "老人编号", example = "1")
    private Long elderId;
    @ApiModelProperty(value = "销售人员姓名", example = "张三")
    private String staffName;
    @ApiModelProperty(value = "预定床位名称", example = "爱心楼-1层-1房-1床")
    private String bedName;
    @ApiModelProperty(value = "老人姓名", example = "张三")
    private String elderName;
    @ApiModelProperty(value = "身份证号", example = "null")
    private String idNum;
    @ApiModelProperty(value = "老人性别", example = "男")
    private String elderSex;
    @ApiModelProperty(value = "老人年龄", example = "67")
    private Integer elderAge;
    @ApiModelProperty(value = "交款人联系电话", example = "13546575678")
    private String payerPhone;
    @ApiModelProperty(value = "定金", example = "1000")
    private BigDecimal deposit;
    @ApiModelProperty(value = "退款状态", example = "否")
    private String reserveFlag;
    @ApiModelProperty(value = "入住状态", example = "预定")
    private String checkFlag;
}
