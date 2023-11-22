package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询入住签约请求实体")
public class PageCheckContractByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "姓名", required = false, example = "张三")
    private String name;
    @ApiModelProperty(value = "性别", required = false, example = "男")
    private String sex;
    @ApiModelProperty(value = "身份证号", required = false, example = "null")
    private String idNum;
}
