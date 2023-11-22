package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "根据编号查询入库记录响应实体")
public class GetWarehouseRecordByIdVo {
    @ApiModelProperty(value = "仓库名称", example = "仓库一")
    private String warehouseName;
    @ApiModelProperty(value = "经办人姓名", example = "张三")
    private String staffName;
    @ApiModelProperty(value = "物资来源", example = "捐赠")
    private String source;
    @ApiModelProperty(value = "入库时间", example = "2022-12-13")
    private Date warehouseDate;
    @ApiModelProperty(value = "入库物资列表", example = "{}")
    private List<GetWarehouseMaterialByIdVo> warehouseMaterialByIdVoList;

    @EqualsAndHashCode(callSuper = true)
    @Data
    @ApiModel(value = "根据编号查询入库物资响应实体")
    public static class GetWarehouseMaterialByIdVo extends Rank {
        @ApiModelProperty(value = "物资名称", example = "勺子")
        private String materialName;
        @ApiModelProperty(value = "入库数量", example = "10")
        private Integer warehouseNum;
        @ApiModelProperty(value = "生产日期", example = "2022-12-12")
        private Date productDate;
        @ApiModelProperty(value = "有效期", example = "2022-12-13")
        private Date expireDate;
    }
}
