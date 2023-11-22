package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询退住审核请求实体")
public class PageRetreatAuditQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "老人姓名", required = false, example = "李四")
    private String elderName;
    @ApiModelProperty(value = "老人性别", required = false, example = "13546565678")
    private String elderSex;
    @ApiModelProperty(value = "老人身份证号", required = false, example = "null")
    private String idNum;
}
