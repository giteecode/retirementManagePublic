package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询出库记录响应实体")
public class PageOutboundRecordByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "仓库名称", example = "仓库")
    private String warehouseName;
    @ApiModelProperty(value = "物资名称", example = "当归")
    private String materialName;
    @ApiModelProperty(value = "出库时间", example = "2022-12-13")
    private Date outboundDate;
    @ApiModelProperty(value = "物资去向", example = "使用")
    private String materialUse;
    @ApiModelProperty(value = "领用人编号", example = "1")
    @JsonIgnore
    private Long recipientId;
    @ApiModelProperty(value = "领用人类型", example = "老人/员工")
    @JsonIgnore
    private String recipientType;
    @ApiModelProperty(value = "领用人", example = "张三")
    private String recipient;
    @ApiModelProperty(value = "登记人", example = "张三")
    private String staffName;
    @ApiModelProperty(value = "出库状态", example = "待审核")
    private String outboundFlag;
}
