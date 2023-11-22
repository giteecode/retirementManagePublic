package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "根据编号获取老人费用详情响应实体")
public class GetElderFeeByIdVo {
    @ApiModelProperty(value = "老人姓名", example = "张三")
    private String elderName;
    @ApiModelProperty(value = "合同开始时间", example = "2022-12-12")
    private Date contractStartTime;
    @ApiModelProperty(value = "合同结束时间", example = "2022-12-13")
    private Date contractEndTime;
    @ApiModelProperty(value = "费用详情列表", example = "{}")
    private List<FeeDetail> feeDetailList;

    @Data
    @ApiModel(value = "费用详情响应实体")
    public static class FeeDetail {
        @ApiModelProperty(value = "消费时间", example = "2023-01")
        private String feeDate;
        @ApiModelProperty(value = "合同内护理费用", example = "1000")
        private BigDecimal contractInsideNurseFee;
        @ApiModelProperty(value = "合同内餐饮费用", example = "1000")
        private BigDecimal contractInsideDishesFee;
        @ApiModelProperty(value = "合同内床位费用", example = "1000")
        private BigDecimal contractInsideBedFee;
        @ApiModelProperty(value = "预定护理费用", example = "200")
        private BigDecimal orderNurseFee;
        @ApiModelProperty(value = "预定套餐费用", example = "200")
        private BigDecimal orderDishesFee;
        @ApiModelProperty(value = "合同外护理费用", example = "500")
        private BigDecimal contractOutsideNurseFee;
        @ApiModelProperty(value = "合同外餐饮费用", example = "500")
        private BigDecimal contractOutsideDishesFee;
        @ApiModelProperty(value = "合同外床位费用", example = "500")
        private BigDecimal contractOutsideBedFee;
        @ApiModelProperty(value = "应缴费用", example = "1500")
        private BigDecimal payableFee;
        @ApiModelProperty(value = "应退费用", example = "500")
        private BigDecimal returnableFee;
    }
}
