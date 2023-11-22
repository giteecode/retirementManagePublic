package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "本月业绩排行响应实体")
public class MonthPerformanceRankVo {
    @ApiModelProperty(value = "咨询客户", example = "1")
    private Long consultClientNum;
    @ApiModelProperty(value = "咨询客户浮动率", example = "1%")
    private Double consultClientFloatRate;
    @ApiModelProperty(value = "签约合同", example = "1")
    private Long signContractNum;
    @ApiModelProperty(value = "签约合同浮动率", example = "1%")
    private Double signContractFloatRate;
    @ApiModelProperty(value = "咨询转换率", example = "1%")
    private Double consultConversionRate;
    @ApiModelProperty(value = "咨询转换率浮动", example = "1%")
    private Double consultConversionFloatRate;
    @ApiModelProperty(value = "业务员销售排行列表", example = "{}")
    private List<SaleRank> saleRankList;

    @Data
    @ApiModel(value = "业务员销售响应实体")
    public static class SaleRank {
        @ApiModelProperty(value = "排名", example = "1")
        private Long rank;
        @ApiModelProperty(value = "业务员", example = "1")
        private String name;
        @ApiModelProperty(value = "接待咨询", example = "1")
        private Long consultNum;
        @ApiModelProperty(value = "签约合同", example = "1")
        private Long contractNum;
    }
}
