package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询预存充值请求实体")
public class PageDepositRechargeByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "姓名", required = false, example = "张三")
    private String name;
    @ApiModelProperty(value = "电话", required = false, example = "13547566879")
    private String phone;
    @ApiModelProperty(value = "身份证号", required = false, example = "null")
    private String idNum;
}
