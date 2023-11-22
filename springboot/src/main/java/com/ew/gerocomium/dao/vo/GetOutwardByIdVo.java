package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "根据编号查询外出登记响应实体")
public class GetOutwardByIdVo {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "老人姓名", example = "张三")
    private String elderName;
    @ApiModelProperty(value = "陪同人姓名", example = "李四")
    private String chaperoneName;
    @ApiModelProperty(value = "陪同人电话", example = "13546574678")
    private String chaperonePhone;
    @ApiModelProperty(value = "陪同人类型", example = "家属")
    private String chaperoneType;
    @ApiModelProperty(value = "外出时间", example = "2022-12-12")
    private Date outwardDate;
    @ApiModelProperty(value = "计划返回时间", example = "2022-12-13")
    private Date planReturnDate;
}
