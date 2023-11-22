package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "合同过期响应实体")
public class ExpireContractVo {
    @ApiModelProperty(value = "老人编号", example = "1")
    private Long elderId;
    @ApiModelProperty(value = "老人姓名", example = "张三")
    private String elderName;
    @ApiModelProperty(value = "身份证号", example = "null")
    private String idNum;
    @ApiModelProperty(value = "合同结束时间", example = "2022-12-24")
    private Date endDate;
}
