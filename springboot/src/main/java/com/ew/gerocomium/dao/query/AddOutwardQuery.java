package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "新增外出登记请求实体")
public class AddOutwardQuery {
    @ApiModelProperty(value = "老人编号", required = true, example = "1")
    private Long elderId;
    @ApiModelProperty(value = "陪同人姓名", required = true, example = "张三")
    private String chaperoneName;
    @ApiModelProperty(value = "陪同人电话", required = true, example = "13546473658")
    private String chaperonePhone;
    @ApiModelProperty(value = "陪同人类型", required = true, example = "家属/护工")
    private String chaperoneType;
    @ApiModelProperty(value = "外出时间", required = true, example = "2022-12-12")
    private String outwardDate;
    @ApiModelProperty(value = "计划返回时间", required = true, example = "2022-12-13")
    private String planReturnDate;
}
