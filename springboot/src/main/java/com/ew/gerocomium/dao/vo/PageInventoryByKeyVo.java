package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询库存响应实体")
public class PageInventoryByKeyVo extends Rank {
    @ApiModelProperty(value = "仓库名称", example = "药库")
    private String warehouseName;
    @ApiModelProperty(value = "物资编号", example = "1")
    @JsonIgnore
    private Long materialId;
    @ApiModelProperty(value = "物资名称", example = "当归")
    private String materialName;
    @ApiModelProperty(value = "总库存", example = "10")
    private Integer total;
    @ApiModelProperty(value = "入库数量", example = "5")
    private Integer warehouseNum;
    @ApiModelProperty(value = "库存数量", example = "3")
    private Integer inventory;
    @ApiModelProperty(value = "出库数量", example = "2")
    private Integer outboundNum;
    @ApiModelProperty(value = "物资单价", example = "100")
    private BigDecimal price;
}
