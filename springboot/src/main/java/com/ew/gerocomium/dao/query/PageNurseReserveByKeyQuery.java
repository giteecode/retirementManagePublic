package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询护理预定请求实体")
public class PageNurseReserveByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "老人姓名", required = false, example = "张三")
    private String elderName;
    @ApiModelProperty(value = "项目名称", required = false, example = "血糖监测")
    private String serviceName;
    @ApiModelProperty(value = "床位名称", required = false, example = "爱心楼-1层-1房-1床")
    private String bedName;
}
