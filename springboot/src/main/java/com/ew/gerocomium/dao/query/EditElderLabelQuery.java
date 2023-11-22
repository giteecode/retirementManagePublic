package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "编辑老人标签请求实体")
public class EditElderLabelQuery {
    @ApiModelProperty(value = "老人编号", required = true, example = "1")
    private Long elderId;
    @ApiModelProperty(value = "标签编号列表", required = true, example = "[1]")
    private List<Long> labelIdList;
}
