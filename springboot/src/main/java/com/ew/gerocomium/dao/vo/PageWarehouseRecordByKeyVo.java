package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询入库记录响应实体")
public class PageWarehouseRecordByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "仓库名称", example = "仓库")
    private String warehouseName;
    @ApiModelProperty(value = "物资名称", example = "当归")
    private String materialName;
    @ApiModelProperty(value = "入库时间", example = "2022-12-13")
    private Date warehouseDate;
    @ApiModelProperty(value = "物资来源", example = "捐赠")
    private String source;
    @ApiModelProperty(value = "经办人", example = "张三")
    private String staffName;
    @ApiModelProperty(value = "入库状态", example = "待审核")
    private String warehouseFlag;
}
