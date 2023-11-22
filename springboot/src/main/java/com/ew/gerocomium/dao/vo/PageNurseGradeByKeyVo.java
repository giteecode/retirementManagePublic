package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询护理等级响应实体")
public class PageNurseGradeByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "护理等级名称", example = "一级护理")
    private String name;
    @ApiModelProperty(value = "护理类型", example = "自理")
    private String type;
    @ApiModelProperty(value = "月护理费用", example = "1000")
    private String monthPrice;
}
