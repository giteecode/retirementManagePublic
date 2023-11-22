package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询房间类型响应实体")
public class PageRoomTypeByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "房间类型名称", example = "单人间")
    private String name;
    @ApiModelProperty(value = "每月价格", example = "500")
    private BigDecimal monthPrice;
}
