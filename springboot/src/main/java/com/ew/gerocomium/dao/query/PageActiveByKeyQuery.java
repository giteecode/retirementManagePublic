package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询活动请求实体")
public class PageActiveByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "活动名称", required = false, example = "文艺汇演")
    private String name;
    @ApiModelProperty(value = "活动类型编号", required = false, example = "1")
    private Long typeId;
    @ApiModelProperty(value = "开始时间", required = false, example = "2022-12-12")
    private String startTime;
    @ApiModelProperty(value = "结束世间", required = false, example = "2022-12-13")
    private String endTime;
}
