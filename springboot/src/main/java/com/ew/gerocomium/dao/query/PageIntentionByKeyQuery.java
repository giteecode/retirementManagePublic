package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询意向客户请求实体")
public class PageIntentionByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "老人姓名", required = false, example = "李四")
    private String elderName;
    @ApiModelProperty(value = "老人电话", required = false, example = "13546565678")
    private String elderPhone;
    @ApiModelProperty(value = "标签编号", required = false, example = "1")
    private Long labelId;
}
