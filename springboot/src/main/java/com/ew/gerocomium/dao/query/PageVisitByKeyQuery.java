package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询来访登记请求实体")
public class PageVisitByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "老人姓名", required = false, example = "张三")
    private String elderName;
    @ApiModelProperty(value = "来访者姓名", required = false, example = "李四")
    private String visitName;
    @ApiModelProperty(value = "来访者电话", required = false, example = "13547567869")
    private String visitPhone;
    @ApiModelProperty(value = "来访状态", required = false, example = "待离开/已离开")
    private String visitFlag;
}
