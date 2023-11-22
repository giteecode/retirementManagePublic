package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询回访计划请求实体")
public class PageVisitPlanQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "老人编号", required = true, example = "1")
    private Long elderId;
    @ApiModelProperty(value = "计划已执行标记", required = true, example = "true")
    private Boolean completeFlag;
}
