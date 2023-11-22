package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登记返回请求实体")
public class RecordReturnQuery {
    @ApiModelProperty(value = "外出登记编号", required = true, example = "1")
    private Long id;
    @ApiModelProperty(value = "实际返回时间", required = true, example = "2022-12-14")
    private String realReturnDate;
}
