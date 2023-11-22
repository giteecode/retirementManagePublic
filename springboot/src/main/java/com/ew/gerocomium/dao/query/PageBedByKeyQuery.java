package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询床位请求实体")
public class PageBedByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "楼栋编号", required = false, example = "1")
    private Long buildId;
    @ApiModelProperty(value = "楼层编号", required = false, example = "1")
    private Long floorId;
    @ApiModelProperty(value = "房间编号", required = false, example = "1")
    private Long roomId;
    @ApiModelProperty(value = "床位状态", required = false, example = "空闲")
    private String bedFlag;
}
