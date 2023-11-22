package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "编辑来访登记请求实体")
public class EditVisitQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "来访者姓名", required = true, example = "李四")
    private String name;
    @ApiModelProperty(value = "来访者电话", required = true, example = "13647563647")
    private String phone;
    @ApiModelProperty(value = "与老人关系", required = true, example = "父子")
    private String relation;
    @ApiModelProperty(value = "来访时间", required = true, example = "2022-12-13")
    private String visitDate;
    @ApiModelProperty(value = "来访者人数", required = true, example = "3")
    private Long visitNum;
}
