package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询咨询请求实体")
public class PageConsultByKeyQuery {
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "条数", required = true, example = "1")
    private Integer pageSize;
    @ApiModelProperty(value = "咨询人姓名", required = false, example = "张三")
    private String consultName;
    @ApiModelProperty(value = "咨询人电话", required = false, example = "13546454678")
    private String consultPhone;
    @ApiModelProperty(value = "老人姓名", required = false, example = "李四")
    private String elderName;
    @ApiModelProperty(value = "老人电话", required = false, example = "13546787689")
    private String elderPhone;
    @ApiModelProperty(value = "开始时间", required = false, example = "2022-10-10")
    private String startTime;
    @ApiModelProperty(value = "结束时间", required = false, example = "2022-10-11")
    private String endTime;
    @ApiModelProperty(value = "来源渠道编号", required = false, example = "1")
    private Long sourceId;
    @ApiModelProperty(value = "接待人编号", required = false, example = "1")
    private Long staffId;
}
