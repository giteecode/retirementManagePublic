package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "入住老人账户充值请求实体")
public class RechargeQuery {
    @ApiModelProperty(value = "老人编号", required = true, example = "1")
    private Long elderId;
    @ApiModelProperty(value = "充值金额", required = true, example = "100")
    private BigDecimal amount;
}
