package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "新增事故登记请求实体")
public class AddAccidentQuery extends EditAccidentQuery{
    @ApiModelProperty(value = "老人编号", required = true, example = "1")
    private Long elderId;
}
