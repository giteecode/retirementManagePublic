package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "根据编号获取编辑意向客户标签响应实体")
public class GetEditElderLabelByIdVo {
    @ApiModelProperty(value = "标签分类名称", example = "兴趣")
    private String name;
    @ApiModelProperty(value = "标签分类名称", example = "兴趣")
    private List<LabelTypeItem> labelTypeItemList;

    @Data
    @ApiModel(value = "标签分类子项响应实体")
    public static class LabelTypeItem {
        @ApiModelProperty(value = "编号", example = "1")
        private Long id;
        @ApiModelProperty(value = "名称", example = "null")
        private String name;
        @ApiModelProperty(value = "老人选中标记", example = "null")
        private Boolean isCheck;
    }
}
