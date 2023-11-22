package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询出库记录请求实体")
public class PageOutboundRecordByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "仓库名称", required = false, example = "药库")
    private String warehouseName;
    @ApiModelProperty(value = "物资名称", required = false, example = "当归")
    private String materialName;
    @ApiModelProperty(value = "开始时间", required = false, example = "2022-12-13")
    private String startTime;
    @ApiModelProperty(value = "结束时间", required = false, example = "2022-12-14")
    private String endTime;
    @ApiModelProperty(value = "领用人", required = false, example = "张三")
    private String recipient;
}
