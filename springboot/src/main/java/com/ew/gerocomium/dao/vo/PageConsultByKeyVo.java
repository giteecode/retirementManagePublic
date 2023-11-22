package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询咨询响应实体")
public class PageConsultByKeyVo extends Rank {
    @ApiModelProperty(value = "咨询人编号", example = "1")
    private Long consultId;
    @ApiModelProperty(value = "老人编号", example = "1")
    private Long elderId;
    @ApiModelProperty(value = "咨询人姓名", example = "张三")
    private String consultName;
    @ApiModelProperty(value = "咨询人电话", example = "13546564789")
    private String consultPhone;
    @ApiModelProperty(value = "老人姓名", example = "李四")
    private String elderName;
    @ApiModelProperty(value = "老人电话", example = "13546565678")
    private String elderPhone;
    @ApiModelProperty(value = "性别", example = "男")
    private String sex;
    @ApiModelProperty(value = "年龄", example = "67")
    private Integer age;
    @ApiModelProperty(value = "咨询日期", example = "1978-12-23")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date consultDate;
    @ApiModelProperty(value = "来源渠道", example = "广告")
    private String sourceName;
    @ApiModelProperty(value = "接待人姓名", example = "王五")
    private String staffName;
}
