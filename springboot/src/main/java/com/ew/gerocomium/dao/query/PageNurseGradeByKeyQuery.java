package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询护理等级请求实体")
public class PageNurseGradeByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "级别名称", required = false, example = "一级护理")
    private String gradeName;
    @ApiModelProperty(value = "护理类型", required = false, example = "自理/介助/介护/其他")
    private String nurseType;
}
