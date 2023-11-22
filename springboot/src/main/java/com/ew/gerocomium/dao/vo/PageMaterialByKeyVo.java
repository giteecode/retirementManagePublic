package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询物资响应实体")
public class PageMaterialByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "物资名称", example = "勺子")
    private String name;
    @ApiModelProperty(value = "物资分类", example = "餐具")
    private String typeName;
    @ApiModelProperty(value = "物资单价", example = "10")
    private String price;
}
