package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "新增回访计划请求实体")
public class AddVisitPlanQuery {
    @ApiModelProperty(value = "老人编号", required = true, example = "1")
    private Long elderId;
    @ApiModelProperty(value = "回访计划标题", required = true, example = "测试标题")
    private String title;
    @ApiModelProperty(value = "计划回访时间", required = true, example = "2022-12-13")
    private String planDate;
}
