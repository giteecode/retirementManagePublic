package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询护理预定响应实体")
public class PageNurseReserveByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "老人姓名", example = "张三")
    private String elderName;
    @ApiModelProperty(value = "床位名称", example = "爱心楼-1层-1房-1床")
    private String bedName;
    @ApiModelProperty(value = "项目名称", example = "血糖监测")
    private String serviceName;
    @ApiModelProperty(value = "所需时间", example = "30")
    private Integer needDate;
    @ApiModelProperty(value = "服务费用", example = "100")
    private BigDecimal servicePrice;
    @ApiModelProperty(value = "收费方式", example = "按次/按月")
    private String chargeMethod;
    @ApiModelProperty(value = "服务次数", example = "2")
    private Integer frequency;
    @ApiModelProperty(value = "支付总额", example = "200")
    private BigDecimal payAmount;
    @ApiModelProperty(value = "护理时间", example = "2020/10/10 12:12:12")
    private String nurseDate;
    @ApiModelProperty(value = "订单状态", example = "待支付/待执行")
    private String orderFlag;
}
