package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询长者请求实体")
public class PageElderByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "老人姓名", required = false, example = "张三")
    private String elderName;
    @ApiModelProperty(value = "身份证号", required = false, example = "null")
    private String idNum;
    @ApiModelProperty(value = "老人性别", required = false, example = "男")
    private String elderSex;
}
