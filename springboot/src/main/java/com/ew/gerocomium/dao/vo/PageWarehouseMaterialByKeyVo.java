package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询仓库物资响应实体")
public class PageWarehouseMaterialByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "物资名称", example = "当归")
    private String materialName;
    @ApiModelProperty(value = "物资单价", example = "100")
    private BigDecimal price;
    @ApiModelProperty(value = "入库数量", example = "10")
    private Integer warehouseNum;
    @ApiModelProperty(value = "库存数量", example = "8")
    private Integer inventory;
    @ApiModelProperty(value = "有效期", example = "2022-12-23")
    private Date expireDate;
}
