package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询服务项目响应实体")
public class PageServiceByKeyVo extends Rank {
    @ApiModelProperty(value = "服务编号", example = "1")
    private Long id;
    @ApiModelProperty(value = "服务名称", example = "心电检测")
    private String name;
    @ApiModelProperty(value = "收费方式", example = "按次/按月")
    private String chargeMethod;
    @ApiModelProperty(value = "服务费用", example = "120")
    private BigDecimal price;
    @ApiModelProperty(value = "所需时间（分）", example = "5")
    private Integer needDate;
}
