package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询消费记录响应实体")
public class PageConsumeByKeyVo extends Rank {
    @ApiModelProperty(value = "老人姓名", example = "李四")
    private String elderName;
    @ApiModelProperty(value = "身份证号", example = "null")
    private String idNum;
    @ApiModelProperty(value = "消费类别", example = "护理")
    private String consumeType;
    @ApiModelProperty(value = "消费金额", example = "1000")
    private BigDecimal consumeAmount;
    @ApiModelProperty(value = "消费日期", example = "2022-12-13")
    private Date consumeDate;
}
