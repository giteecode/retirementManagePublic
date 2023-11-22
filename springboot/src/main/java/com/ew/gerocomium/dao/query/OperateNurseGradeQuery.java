package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "操作护理等级请求实体")
public class OperateNurseGradeQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "护理等级名称", required = true, example = "一级护理")
    private String name;
    @ApiModelProperty(value = "护理类型", required = true, example = "自理")
    private String type;
    @ApiModelProperty(value = "月护理费用", required = true, example = "1000")
    private BigDecimal monthPrice;
    @ApiModelProperty(value = "服务编号列表", required = true, example = "{}")
    private List<Long> serviceIdList;
}
