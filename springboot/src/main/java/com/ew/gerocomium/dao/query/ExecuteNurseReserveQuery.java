package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "执行护理预定请求实体")
public class ExecuteNurseReserveQuery {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "服务员工编号", required = true, example = "1")
    private Long staffId;
    @ApiModelProperty(value = "护理时间", required = true, example = "2022-12-13")
    private String nurseDate;
}
