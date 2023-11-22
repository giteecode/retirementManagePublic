package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询预存充值响应实体")
public class PageDepositRechargeByKeyVo extends Rank {
    @ApiModelProperty(value = "老人编号", example = "1")
    private String elderId;
    @ApiModelProperty(value = "老人姓名", example = "李四")
    private String elderName;
    @ApiModelProperty(value = "老人电话", example = "13546565678")
    private String elderPhone;
    @ApiModelProperty(value = "身份证号", example = "null")
    private String idNum;
    @ApiModelProperty(value = "床位名称", example = "爱心楼-1层-1房-1床")
    private String bedName;
    @ApiModelProperty(value = "余额", example = "1000")
    private BigDecimal balance;
}
