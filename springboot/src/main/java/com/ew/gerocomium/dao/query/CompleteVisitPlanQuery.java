package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "执行回访计划请求实体")
public class CompleteVisitPlanQuery {
    @ApiModelProperty(value = "回访计划编号", required = true, example = "1")
    private Long id;
    @ApiModelProperty(value = "回访计划标题", required = true, example = "测试内容")
    private String content;
    @ApiModelProperty(value = "计划完成时间", required = true, example = "2022-12-13")
    private String completeDate;
}
