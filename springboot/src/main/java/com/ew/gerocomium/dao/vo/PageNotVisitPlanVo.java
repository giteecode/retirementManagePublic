package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询回访计划响应实体")
public class PageNotVisitPlanVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "回访计划标题", example = "测试标题")
    private String title;
    @ApiModelProperty(value = "计划回访时间", example = "2022-12-13")
    private Date planDate;
}
