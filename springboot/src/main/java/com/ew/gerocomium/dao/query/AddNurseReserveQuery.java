package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "新增护理预定请求实体")
public class AddNurseReserveQuery {
    @ApiModelProperty(value = "老人编号", required = true, example = "1")
    private Long elderId;
    @ApiModelProperty(value = "项目名称", required = true, example = "血糖监测")
    private String serviceName;
    @ApiModelProperty(value = "所需时间", required = true, example = "30")
    private Integer needDate;
    @ApiModelProperty(value = "服务费用", required = true, example = "100")
    private BigDecimal servicePrice;
    @ApiModelProperty(value = "收费方式", required = true, example = "按次/按月")
    private String chargeMethod;
    @ApiModelProperty(value = "服务次数", required = true, example = "2")
    private Integer frequency;
    @ApiModelProperty(value = "支付总额", required = true, example = "200")
    private BigDecimal payAmount;
}
