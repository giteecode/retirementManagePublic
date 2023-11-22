package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "新增来访登记请求实体")
public class AddVisitQuery extends EditVisitQuery {
    @ApiModelProperty(value = "老人编号", required = true, example = "1")
    private Long elderId;
}
