package com.ew.gerocomium.dao.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Rank {
    @ApiModelProperty(value = "序号", example = "1")
    private Long rank;
}
