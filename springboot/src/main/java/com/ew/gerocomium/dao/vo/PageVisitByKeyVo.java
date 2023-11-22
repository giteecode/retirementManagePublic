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
@ApiModel(value = "分页查询来访登记响应实体")
public class PageVisitByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "老人姓名", example = "张三")
    private String elderName;
    @ApiModelProperty(value = "来访者姓名", example = "李四")
    private String visitName;
    @ApiModelProperty(value = "来访者电话", example = "13647563647")
    private String visitPhone;
    @ApiModelProperty(value = "与老人关系", example = "父子")
    private String relation;
    @ApiModelProperty(value = "来访状态", example = "待离开")
    private String visitFlag;
    @ApiModelProperty(value = "来访时间", example = "2022-12-13")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date visitDate;
    @ApiModelProperty(value = "离开时间", example = "2022-12-13")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date leaveDate;
    @ApiModelProperty(value = "来访者人数", example = "3")
    private Integer visitNum;
}
