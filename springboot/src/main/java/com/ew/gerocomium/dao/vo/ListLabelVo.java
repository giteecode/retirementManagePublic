package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "获取客户标签列表响应实体")
public class ListLabelVo {
    @ApiModelProperty(value = "标签分类编号", example = "1")
    private Long id;
    @ApiModelProperty(value = "标签分类名称", example = "兴趣")
    private String name;
    @ApiModelProperty(value = "标签分类子项列表", example = "{}")
    private List<LabelItem> labelItemList;

    @Data
    @ApiModel(value = "获取客户标签列表响应实体")
    public static class LabelItem {
        @ApiModelProperty(value = "标签编号", example = "1")
        private Long id;
        @ApiModelProperty(value = "标签分类编号", example = "1")
        private Long typeId;
        @ApiModelProperty(value = "标签名称", example = "兴趣")
        private String name;
        @ApiModelProperty(value = "标签颜色", example = "rgba(144, 238, 144, 1)")
        private String color;
    }
}
