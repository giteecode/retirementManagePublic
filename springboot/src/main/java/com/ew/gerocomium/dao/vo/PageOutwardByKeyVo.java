package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询外出登记响应实体")
public class PageOutwardByKeyVo extends Rank {
    @ApiModelProperty(value = "外出登记编号", example = "1")
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date outwardDate;
    @ApiModelProperty(value = "计划返回时间", example = "2022-12-13")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date planReturnDate;
    @ApiModelProperty(value = "实际返回时间", example = "2022-12-14")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date realReturnDate;
}
