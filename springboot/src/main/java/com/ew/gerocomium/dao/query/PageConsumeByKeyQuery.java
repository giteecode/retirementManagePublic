package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询消费记录请求实体")
public class PageConsumeByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "老人姓名", required = false, example = "张三")
    private String elderName;
    @ApiModelProperty(value = "开始时间", required = false, example = "2022-12-12")
    private String startTime;
    @ApiModelProperty(value = "结束时间", required = false, example = "2022-12-13")
    private String endTime;
}
