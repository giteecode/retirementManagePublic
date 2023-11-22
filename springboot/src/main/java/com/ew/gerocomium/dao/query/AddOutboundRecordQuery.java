package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "新增出库记录请求实体")
public class AddOutboundRecordQuery {
    @ApiModelProperty(value = "领用人类型", required = true, example = "老人/员工")
    private String recipientType;
    @ApiModelProperty(value = "领用人编号", required = true, example = "1")
    private Long recipientId;
    @ApiModelProperty(value = "仓库编号", required = true, example = "1")
    private Long warehouseId;
    @ApiModelProperty(value = "出库时间", required = true, example = "2022-12-14")
    private String outboundDate;
    @ApiModelProperty(value = "物资去向", required = true, example = "使用")
    private String materialUse;
    @ApiModelProperty(value = "登记人编号", required = true, example = "1")
    private Long staffId;
    @ApiModelProperty(value = "出库物资列表", required = true, example = "[{'warehouseMaterialId':1,'outboundNum':10}]")
    private List<AddOutboundMaterialQuery> outboundMaterialQueryList;

    @Data
    @ApiModel(value = "新增出库物资请求实体")
    public static class AddOutboundMaterialQuery {
        @ApiModelProperty(value = "入库物资编号", required = true, example = "1")
        private Long warehouseMaterialId;
        @ApiModelProperty(value = "出库数量", required = true, example = "10")
        private Integer outboundNum;
    }
}
