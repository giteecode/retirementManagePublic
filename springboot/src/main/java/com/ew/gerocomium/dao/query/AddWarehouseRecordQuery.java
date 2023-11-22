package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "新增入库记录请求实体")
public class AddWarehouseRecordQuery {
    @ApiModelProperty(value = "仓库编号", required = true, example = "1")
    private Long warehouseId;
    @ApiModelProperty(value = "经办人编号", required = true, example = "1")
    private Long staffId;
    @ApiModelProperty(value = "物资来源", required = true, example = "捐赠")
    private String source;
    @ApiModelProperty(value = "入库时间", required = true, example = "2022-12-13")
    private String warehouseDate;
    @ApiModelProperty(value = "入库物资列表", required = true, example = "[{'materialId':1,'warehouseNum':10,'productDate':'2022-12-12','expireDate':'2022-12-13'}]")
    private List<AddWarehouseMaterialQuery> warehouseMaterialQueryList;

    @Data
    @ApiModel(value = "新增入库物资请求实体")
    public static class AddWarehouseMaterialQuery {
        @ApiModelProperty(value = "物资编号", required = true, example = "1")
        private Long materialId;
        @ApiModelProperty(value = "入库数量", required = true, example = "10")
        private Integer warehouseNum;
        @ApiModelProperty(value = "生产日期", required = true, example = "2022-12-12")
        private String productDate;
        @ApiModelProperty(value = "有效期", required = true, example = "2022-12-13")
        private String expireDate;
    }
}
