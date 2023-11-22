package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询活动分类响应实体")
public class PageActiveTypeByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "活动分类名称", example = "文艺演出")
    private String name;
}
