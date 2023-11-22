package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询退住申请请求实体")
public class PageRetreatApplyQuery extends PageRetreatAuditQuery{
    @ApiModelProperty(value = "床位名称", required = false, example = "爱心楼-1层-1房-1床")
    private String bedName;
}
