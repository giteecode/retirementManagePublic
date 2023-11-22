package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询来源渠道请求实体")
public class PageSourceByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "来源渠道名称", required = false, example = "广告")
    private String sourceName;
}
