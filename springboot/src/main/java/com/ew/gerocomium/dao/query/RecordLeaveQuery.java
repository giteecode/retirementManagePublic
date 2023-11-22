package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登记离开请求实体")
public class RecordLeaveQuery {
    @ApiModelProperty(value = "来访登记编号", required = true, example = "1")
    private Long id;
    @ApiModelProperty(value = "离开时间", required = true, example = "2022-12-14")
    private String leaveDate;
}
